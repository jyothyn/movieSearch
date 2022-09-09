package com.cleanarch.moviesearch.data.repository

import com.cleanarch.moviesearch.data.model.MovieSearchResult
import com.cleanarch.moviesearch.data.network.ApiService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    SearchRepository {

//    override suspend fun submitQuery(query: String): Flow<MovieSearchResult> {
//        return flow {
//            apiService.submitQuery(query).also { emit(it) }
//        }
//    }

    override suspend fun submitSearch(query: String): MovieSearchResult {
        return apiService.submitQuery(query)
    }
}
