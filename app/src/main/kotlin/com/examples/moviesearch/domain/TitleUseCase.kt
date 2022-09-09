package com.examples.moviesearch.domain

import com.examples.moviesearch.data.model.MovieSearchResult
import com.examples.moviesearch.data.repository.SearchRepository
import com.examples.moviesearch.domain.entity.Movie
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
    //    suspend operator fun invoke(str:String):  List<Movie> {
//        val response = repository.submitSearch(str)
//        //here you can add some domain logic or call another UseCase
//        return response.toDomain()
//    }
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