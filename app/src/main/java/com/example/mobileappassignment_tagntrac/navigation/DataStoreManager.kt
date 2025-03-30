package com.example.mobileappassignment_tagntrac.navigation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create the DataStore at the top-level
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class DataStoreManager(private val context: Context) {

    // Define keys
    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // Get the login state as a Flow
    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // Get our show completed value, defaulting to false if not set:
            preferences[IS_LOGGED_IN] ?: false
        }

    // Update the login state
    suspend fun setLoggedIn(loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = loggedIn
        }
    }
}