package com.example.playground.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.data.common.Result
import com.example.playground.domain.model.Movie
import com.example.playground.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private var getMoviesUseCase: GetMoviesUseCase) : ViewModel() {
    private val _resultMovies = Channel<Result<List<Movie>>>(Channel.BUFFERED)
    val resultMovies: Flow<Result<List<Movie>>> = _resultMovies.receiveAsFlow()

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) { getMoviesUseCase.getMovies().collect { _resultMovies.send(it) } }
    }
}
