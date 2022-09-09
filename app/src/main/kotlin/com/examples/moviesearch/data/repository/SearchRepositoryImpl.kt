package com.examples.moviesearch.data.repository

import com.examples.moviesearch.data.model.MovieSearchResult
import com.examples.moviesearch.data.network.ApiService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    SearchRepository {

//    override suspend fun submitQuery(url: String, query: String, i: String?, key: String?):
//            Flow<MovieSearchResult> {
//        return flow<MovieSearchResult> {
//            url.let {
//                apiService.submitQuery(url, i ?: "tt3896198", key ?: "6d53ef23", query)
//                    .also {
//                        emit(it)
//                    }
//            }
//        }//.flowOn(Dispatchers.IO)
//    }

//    override suspend fun submitQuery(query: String): Flow<MovieSearchResult> {
//        return flow {
//            apiService.submitQuery(query).also { emit(it) }
//        }
//    }

    override suspend fun submitSearch(query: String): MovieSearchResult {
        return apiService.submitQuery(query)
    }
}
