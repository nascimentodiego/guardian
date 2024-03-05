package br.com.bit.guardian.login.domain.mappers

import br.com.bit.guardian.login.domain.entities.User
import br.com.bit.guardian.login.data.repository.model.User as UserRepository

fun UserRepository.toUser() = User(name = this.name, email = this.email)