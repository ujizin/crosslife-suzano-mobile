package br.com.crosslife.features.recoverypassword.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.Screen
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import br.com.crosslife.data.Result
import br.com.crosslife.data.errors.PasswordNotEqualsError
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ViewModelExtensions {

    private val token = savedStateHandle.get<String>(Screen.RecoveryPassword.TOKEN_ARG).orEmpty()

    val recoveryPasswordState: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun changePassword(newPassword: String, confirmNewPassword: String) {
        userRepository.changePasswordWithToken(
            token,
            newPassword,
        ).onStart {
            check(newPassword == confirmNewPassword) { throw PasswordNotEqualsError() }
        }.notify(viewModelScope, recoveryPasswordState())
    }
}