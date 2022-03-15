package dev.surehand.cleanapp.data.repository

import dev.surehand.cleanapp.data.storage.User
import dev.surehand.cleanapp.data.storage.UserStorage
import dev.surehand.cleanapp.domain.model.SaveUserNameParam
import dev.surehand.cleanapp.domain.model.UserName
import dev.surehand.cleanapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {

        val user = mapToStorage(saveParam)

        return userStorage.save(user)
    }

    override fun getName(): UserName {

        val user = userStorage.get()

        return mapToDomain(user)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(firstName = saveParam.name, lastName = "")
    }

    private fun mapToDomain(user: User): UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}