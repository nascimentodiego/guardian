package br.com.bit.guardian.datasource.local.model

import br.com.bit.guardian.core.datastore.UserPreferences

data class UserStorage(
    val uuid: String,
    val name: String,
    val email: String,
    val photoUrl: String
)

internal fun UserPreferences.toUserStorage() =
    UserStorage(
        this.uuid,
        this.name,
        this.email,
        this.photoUrl
    )
