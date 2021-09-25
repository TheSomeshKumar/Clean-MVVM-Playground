package com.example.playground.domain.usecase

import com.example.playground.domain.repository.PlaygroundRepository
import javax.inject.Inject

class TvShowUseCase @Inject constructor(private val playgroundRepository: PlaygroundRepository) {
    suspend fun getTvShow() = playgroundRepository.getTvShow()
}
