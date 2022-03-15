package dev.surehand.cleanapp.domain.usecase

import dev.surehand.cleanapp.domain.model.UserName
import dev.surehand.cleanapp.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }
}