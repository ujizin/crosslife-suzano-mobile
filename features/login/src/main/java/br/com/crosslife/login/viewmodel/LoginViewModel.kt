package br.com.crosslife.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.catchNetwork
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.ServerError
import br.com.crosslife.domain.model.Status
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val login: StateFlow<LoginViewState> = MutableStateFlow(LoginViewState.Initial)

    fun fetchLogin(username: String, password: String) {
        userRepository.fetchLogin(username, password)
            .onStart { login().value = LoginViewState.Loading }
            .catchNetwork { error ->
                login().value = when (error.status) {
                    Status.UNAUTHORIZED -> LoginViewState.InvalidAccount
                    else -> LoginViewState.Error(error)
                }
            }
            .onEach { login().value = LoginViewState.Success }
            .launchIn(viewModelScope)
    }
}

sealed class LoginViewState {
    object Initial : LoginViewState()
    object Loading : LoginViewState()
    data class Error(val error: ServerError) : LoginViewState()
    object Success : LoginViewState()
    object InvalidAccount : LoginViewState()
}