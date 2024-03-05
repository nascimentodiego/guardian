package br.com.bit.guardian.login.data.datasource.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val email:String,
    val password:String
)
