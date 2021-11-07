package br.com.crosslife.features.changepassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.data.Result
import br.com.crosslife.data.errors.PasswordNotEqualsError
import br.com.crosslife.domain.preferences.UserStore
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userStore: UserStore,
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val result: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun changePassword(password: String, newPassword: String, confirmNewPassword: String) {
        userStore.getUsername().onStart {
            check(newPassword == confirmNewPassword) { throw PasswordNotEqualsError() }
        }.flatMapConcat { username ->
            userRepository.changePassword(username, password, newPassword)
        }.notify(stateFlow = result())
            .launchIn(viewModelScope)
    }
}
