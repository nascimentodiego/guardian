package br.com.bit.guardian.datasource.local

import br.com.bit.guardian.datasource.local.model.UserStorage
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    fun getUserPreferences(): Flow<UserStorage>
    fun updateUSerPreference(pref: UserStorage): Flow<UserStorage>
}