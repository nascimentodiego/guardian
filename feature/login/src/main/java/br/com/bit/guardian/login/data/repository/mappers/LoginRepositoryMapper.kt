package br.com.bit.guardian.login.data.repository.mappers

import br.com.bit.guardian.login.data.datasource.remote.response.UserLoginResponse
import br.com.bit.guardian.login.data.repository.model.User

fun UserLoginResponse.toUser() = User(
    name = this.name,
    email = this.email,
    photoUrl = this.photoUrl
)