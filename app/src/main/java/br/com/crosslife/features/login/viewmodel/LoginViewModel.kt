package br.com.crosslife.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.data.Result
import br.com.crosslife.data.repositories.user.UserRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _login = MutableStateFlow<Result<User>>(Result.Initial)
    val login: StateFlow<Result<User>> get() = _login

    fun fetchLogin(username: String, password: String) {
        userRepository.fetchLogin(username, password)
            .notify(viewModelScope, _login)
    }
}