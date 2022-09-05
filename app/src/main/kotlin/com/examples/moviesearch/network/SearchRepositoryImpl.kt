package com.examples.moviesearch.network

import com.examples.moviesearch.model.MovieSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun submitQuery(query: String): Flow<MovieSearchResult> {
        return flow {
            apiService.submitQuery(query).also { emit(it) }
        }
    }
}
