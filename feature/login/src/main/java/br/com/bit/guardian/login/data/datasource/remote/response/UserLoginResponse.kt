package br.com.bit.guardian.login.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class UserLoginResponse(
    val uuid:String? = null,
    val name:String,
    val email:String,
    @SerialName("photo_url") val photoUrl:String
)
