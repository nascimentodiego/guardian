package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.datasource.local.UserPreferencesDataSource
import br.com.bit.guardian.datasource.local.model.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserPreferencesDataSource : UserPreferencesDataSource {

    private var stored = UserStorage(uuid = "", name = "", email = "", photoUrl = "")

    override fun getUserPreferences(): Flow<UserStorage> = flowOf(stored)

    override fun updateUSerPreference(pref: UserStorage): Flow<UserStorage> {
        stored = pref
        return flowOf(pref)
    }
}
