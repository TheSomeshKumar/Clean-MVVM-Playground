package com.example.playground.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.data.common.Result
import com.example.playground.domain.model.People
import com.example.playground.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private var getPeopleUseCase: GetPeopleUseCase) : ViewModel() {
    private val _resultPeople = Channel<Result<List<People>>>(Channel.BUFFERED)
    val resultPeople = _resultPeople.receiveAsFlow()

    fun getPeople() {
        viewModelScope.launch { getPeopleUseCase.getPeople().collect { _resultPeople.send(it) } }
    }
}
