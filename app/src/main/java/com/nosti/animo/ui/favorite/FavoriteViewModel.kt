package com.nosti.animo.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nosti.animo.ui.common.ScopedViewModel
import com.nosti.domain.AnimeData
import com.nosti.usecases.GetPopularAnimes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getPopularAnimes: GetPopularAnimes,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val uiModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (uiModel.value == null) refresh()
            return uiModel
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val animes: List<AnimeData>) : UiModel()
        class Navigation(val anime: AnimeData) : UiModel()
        object showUi : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        uiModel.value = UiModel.showUi
    }

    fun showUi() {
        launch {
            uiModel.value = UiModel.Loading
            uiModel.value =
                UiModel.Content(getPopularAnimes.invokeFavorite())
        }
    }

    fun onAnimeClicked(anime: AnimeData) {
        uiModel.value = UiModel.Navigation(anime)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}