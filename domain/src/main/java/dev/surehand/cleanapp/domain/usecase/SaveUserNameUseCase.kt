package dev.surehand.cleanapp.domain.usecase

import dev.surehand.cleanapp.domain.model.SaveUserNameParam
import dev.surehand.cleanapp.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {
        val oldUserName = userRepository.getName()

        if (oldUserName.firstName == param.name) {
            return true
        }

        return userRepository.saveName(saveParam = param)
    }
}