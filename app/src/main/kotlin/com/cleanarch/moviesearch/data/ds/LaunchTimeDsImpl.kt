package com.cleanarch.moviesearch.data.ds

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private const val DS_KEY = "movies_data_store"

private val Context.ds: DataStore<Preferences> by preferencesDataStore(name = DS_KEY)

class LaunchTimeDsImpl(@ApplicationContext context: Context) : LaunchTimeDS {
    private val dataStore: DataStore<Preferences> = context.ds

    private object PreferencesKeys {
        val LAUNCH_TIME = stringPreferencesKey(DS_KEY)
    }

    private val currTime: String =
        LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))

    override suspend fun setLaunchTime() {
        dataStore.edit {
            it[PreferencesKeys.LAUNCH_TIME] = currTime
        }
    }

    override val lastLaunchTime: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.LAUNCH_TIME] ?: currTime
        }
}