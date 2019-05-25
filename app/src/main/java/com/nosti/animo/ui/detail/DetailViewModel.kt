package com.nosti.animo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nosti.animo.model.AnimeData

class DetailViewModel(private val anime: AnimeData) : ViewModel() {

    class UiModel(val anime: AnimeData)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(anime)
            return _model
        }
}