package br.com.bit.guardian.datasource.remote.response.login

data class UserLoginResponse(
    val uuid:String,
    val name:String,
    val email:String,
    val photoUrl:String
)
