package br.com.bit.guardian.registration.domain.mappers

import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.data.repository.model.User as UserRepository

fun UserRepository.toUser() = User(name = this.name, email = this.email)