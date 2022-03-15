package dev.surehand.cleanapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.surehand.cleanapp.domain.model.SaveUserNameParam
import dev.surehand.cleanapp.domain.model.UserName
import dev.surehand.cleanapp.domain.usecase.GetUserNameUseCase
import dev.surehand.cleanapp.domain.usecase.SaveUserNameUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable

    fun save(text: String) {
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param = params)
        resultLiveMutable.value = "Save result = $resultData"
    }

    fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        resultLiveMutable.value = "${userName.firstName} ${userName.lastName}"
    }

}