package dev.surehand.cleanapp.domain.repository

import dev.surehand.cleanapp.domain.model.SaveUserNameParam
import dev.surehand.cleanapp.domain.model.UserName

interface UserRepository {

    fun getName(): UserName

    fun saveName(saveParam: SaveUserNameParam): Boolean
}