package br.com.crosslife.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.data.Result
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.domain.models.User
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val login: StateFlow<Result<User>> = MutableStateFlow(Result.Initial)
    val logout: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)
    val forgotPassword: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun fetchLogin(username: String, password: String) {
        userRepository.fetchLogin(username, password)
            .notify(viewModelScope, login())
    }

    fun fetchLogout() {
        userRepository.fetchLogout()
            .notify(viewModelScope, logout())
    }

    fun fetchForgotPassword(username: String) {
        userRepository.fetchPassword(username)
            .notify(viewModelScope, forgotPassword())
    }
}