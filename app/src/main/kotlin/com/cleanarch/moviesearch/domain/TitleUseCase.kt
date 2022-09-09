package com.cleanarch.moviesearch.domain

import com.cleanarch.moviesearch.data.model.MovieSearchResult
import com.cleanarch.moviesearch.data.repository.SearchRepository
import com.cleanarch.moviesearch.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}

class TitleUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(str: String): Flow<State<List<Movie>>> = flow {
        emit(State.LoadingState)
        try {
            val response = repository.submitSearch(str).toDomain()
            emit(State.DataState((response)))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }

    private fun MovieSearchResult.toDomain(): List<Movie> {
        val movieList: List<Movie>? = this.moviesList?.map {
            Movie(id = it.id, it.title, it.year, it.type, it.poster)
        }
        return movieList ?: emptyList()
    }
}