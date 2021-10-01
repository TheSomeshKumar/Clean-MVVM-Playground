package com.example.playground.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.data.common.Result
import com.example.playground.domain.model.TvShow
import com.example.playground.domain.usecase.GetTvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private var getTvShowUseCase: GetTvShowUseCase) : ViewModel() {
    private val _resultTvShow = Channel<Result<List<TvShow>>>(Channel.BUFFERED)
    val resultTvShow = _resultTvShow.receiveAsFlow()

    fun getTvShow() {
        viewModelScope.launch { getTvShowUseCase.getTvShow().collect { _resultTvShow.send(it) } }
    }
}
