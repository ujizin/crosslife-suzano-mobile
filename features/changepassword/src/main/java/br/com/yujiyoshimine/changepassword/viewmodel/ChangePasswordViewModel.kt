package br.com.yujiyoshimine.changepassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.notify
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.commons.utils.PasswordNotEqualsError
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val result: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun changePassword(password: String, newPassword: String, confirmNewPassword: String) {
        userRepository.getUsername().onStart {
            check(newPassword == confirmNewPassword) { throw PasswordNotEqualsError() }
        }.flatMapConcat { username ->
            userRepository.changePassword(username, password, newPassword)
        }.notify(stateFlow = result())
            .launchIn(viewModelScope)
    }
}
