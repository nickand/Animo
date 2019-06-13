package com.nosti.animo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nosti.animo.model.AnimeData
import com.nosti.animo.model.server.AnimoRepository
import com.nosti.animo.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class AnimoViewModel(private val animoRepository: AnimoRepository) : ScopedViewModel() {

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
            uiModel.value = UiModel.Content(animoRepository.findPopularAnimes())
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