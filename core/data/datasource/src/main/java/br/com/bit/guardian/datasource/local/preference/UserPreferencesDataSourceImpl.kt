package br.com.bit.guardian.datasource.local.preference

import android.content.Context
import br.com.bit.guardian.core.datastore.UserPreferences
import br.com.bit.guardian.core.datastore.userPreferencesStore
import br.com.bit.guardian.datasource.local.UserPreferencesDataSource
import br.com.bit.guardian.datasource.local.model.UserStorage
import br.com.bit.guardian.datasource.local.model.toUserStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferencesDataSource {
    override fun getUserPreferences(): Flow<UserStorage> =
        context.userPreferencesStore.data.map(UserPreferences::toUserStorage)


    override fun updateUSerPreference(pref: UserStorage): Flow<UserStorage> =
        flow {
            val userPreferences = context.userPreferencesStore.updateData {
                UserPreferences(
                    pref.uuid,
                    pref.name,
                    pref.email,
                    pref.photoUrl
                )
            }
            emit(userPreferences.toUserStorage())
        }

}