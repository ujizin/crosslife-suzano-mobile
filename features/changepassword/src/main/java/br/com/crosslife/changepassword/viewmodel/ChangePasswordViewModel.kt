package br.com.crosslife.changepassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.notify
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.PasswordNotEqualsError
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.onStart
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
        }.notify(viewModelScope, result())
    }
}
