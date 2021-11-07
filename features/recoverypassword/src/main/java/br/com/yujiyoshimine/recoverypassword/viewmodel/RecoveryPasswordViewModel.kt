package br.com.yujiyoshimine.recoverypassword.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.notify
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.domain.model.PasswordNotEqualsError
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.repository.UserRepository
import br.com.yujiyoshimine.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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