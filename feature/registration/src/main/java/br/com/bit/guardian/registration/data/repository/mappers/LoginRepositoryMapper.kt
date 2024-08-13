package br.com.bit.guardian.registration.data.repository.mappers

import br.com.bit.guardian.registration.data.repository.model.User
import br.com.bit.guardian.registration.data.datasource.remote.response.UserLoginResponse

fun UserLoginResponse.toUser() = User(
    name = this.name,
    email = this.email,
    photoUrl = this.photoUrl
)