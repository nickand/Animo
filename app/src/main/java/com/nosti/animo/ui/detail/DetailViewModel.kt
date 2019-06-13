package com.nosti.animo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nosti.animo.model.AnimeData
import com.nosti.animo.model.server.AnimoRepository
import com.nosti.animo.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val anime: AnimeData, private val animeId: Int,
                      private val animoRepository: AnimoRepository) : ScopedViewModel() {

    class UiModel(val anime: AnimeData)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findAnime()
            return _model
        }

    private fun findAnime() = launch {
        _model.value = UiModel(animoRepository.findById(animeId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.anime?.let {
            val updatedAnime = it.copy(favorite = !it.favorite)
            _model.value = UiModel(updatedAnime)
            animoRepository.update(updatedAnime)
        }
    }
}