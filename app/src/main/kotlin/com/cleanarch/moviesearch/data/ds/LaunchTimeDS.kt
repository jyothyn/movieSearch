package com.cleanarch.moviesearch.data.ds

import kotlinx.coroutines.flow.Flow

interface LaunchTimeDS {
    suspend fun setLaunchTime()
    val lastLaunchTime: Flow<String>
}