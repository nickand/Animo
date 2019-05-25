package com.nosti.animo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nosti.animo.model.Anime
import com.nosti.animo.model.AnimeData
import com.nosti.animo.model.AnimoRepository
import com.nosti.animo.ui.common.Scope
import kotlinx.coroutines.launch

class AnimoViewModel(private val animoRepository: AnimoRepository) : ViewModel(),
    Scope by Scope.Impl {

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
    }

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            uiModel.value = UiModel.Loading
            uiModel.value = UiModel.Content(animoRepository.getAnimes().data)
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