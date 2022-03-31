package dev.surehand.cleanapp.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import dev.surehand.cleanapp.domain.model.SaveUserNameParam
import dev.surehand.cleanapp.domain.model.UserName
import dev.surehand.cleanapp.domain.usecase.GetUserNameUseCase
import dev.surehand.cleanapp.domain.usecase.SaveUserNameUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    private val saveUserNameUseCase = mock<SaveUserNameUseCase>()
    private val getUserNameUseCase = mock<GetUserNameUseCase>()

    @BeforeEach
    fun setUp() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @AfterEach
    fun tearDown() {
        Mockito.reset(saveUserNameUseCase)
        Mockito.reset(getUserNameUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @Test
    fun `should save username and return true`() {

        val testSaveText = "Test user name"
        val saveResult = true
        val testParams = SaveUserNameParam(name = testSaveText)

        val viewModel = MainViewModel(
            saveUserNameUseCase = saveUserNameUseCase,
            getUserNameUseCase = getUserNameUseCase
        )

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        viewModel.save(text = testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should save username and return false`() {

        val testSaveText = "Test user name"
        val saveResult = false
        val testParams = SaveUserNameParam(name = testSaveText)

        val viewModel = MainViewModel(
            saveUserNameUseCase = saveUserNameUseCase,
            getUserNameUseCase = getUserNameUseCase
        )

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        viewModel.save(text = testSaveText)

        val expected = "Save result = false"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should load username`() {
        val testUserName = UserName(
            firstName = "Test first name",
            lastName = "Test last name"
        )

        Mockito.`when`(getUserNameUseCase.execute()).thenReturn(testUserName)

        val viewModel = MainViewModel(
            saveUserNameUseCase = saveUserNameUseCase,
            getUserNameUseCase = getUserNameUseCase
        )

        viewModel.load()

        val expected = "${testUserName.firstName} ${testUserName.lastName}"
        val actual = viewModel.resultLive.value

        Mockito.verify(getUserNameUseCase, Mockito.times(1))
            .execute()
        Assertions.assertEquals(expected, actual)
    }
}