package dev.surehand.cleanapp.data.storage

interface UserStorage {

    fun save(user: User): Boolean

    fun get(): User
}