package br.com.bit.guardian.registration.ui.login

interface LoginListener {
    fun onCreateUserClickListener()
    fun onLoginClickListener(email:String,password:String)
}