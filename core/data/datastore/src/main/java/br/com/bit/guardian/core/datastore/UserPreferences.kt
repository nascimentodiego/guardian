package br.com.bit.guardian.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

data class UserPreferences(
    val uuid: String,
    val name: String,
    val email: String,
    val photoUrl: String
) {
    companion object {
        fun getDefaultInstance() = UserPreferences(
            uuid = "",
            name = "",
            email = "",
            photoUrl = ""
        )
    }
}

val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.proto",
    serializer = UserPreferencesSerializer
)
