package br.com.bit.guardian.registration.domain.entities

data class User(val name:String,val email:String){
    companion object {
        val Empty = User(" "," ")
    }
}
