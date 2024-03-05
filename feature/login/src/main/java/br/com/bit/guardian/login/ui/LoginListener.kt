package br.com.bit.guardian.login.ui

interface LoginListener {
    fun onCreateUserClickListener(email:String,password:String)
    fun onLoginClickListener(email:String,password:String)
}