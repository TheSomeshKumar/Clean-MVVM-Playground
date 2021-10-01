package com.example.playground.domain.usecase

import com.example.playground.domain.repository.PlaygroundRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val playgroundRepository: PlaygroundRepository) {
    suspend fun getMovies() = playgroundRepository.getMovies()
}
