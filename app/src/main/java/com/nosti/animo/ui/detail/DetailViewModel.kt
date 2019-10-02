package com.nosti.animo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nosti.animo.ui.common.ScopedViewModel
import com.nosti.domain.AnimeData
import com.nosti.usecases.FindAnimeById
import com.nosti.usecases.ToggleAnimeFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val animeId: Int,
    private val findAnimeById: FindAnimeById,
    private val toggleAnimeFavorite: ToggleAnimeFavorite,
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    class UiModel(val anime: AnimeData)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findAnime()
            return _model
        }

    private fun findAnime() = launch {
        _model.value = UiModel(findAnimeById.invoke(animeId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.anime?.let {
            _model.value = UiModel(toggleAnimeFavorite.invoke(it))
        }
    }
}