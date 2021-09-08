package br.com.crosslife.features.changepassword.viewmodel

import br.com.crosslife.data.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.domain.models.User
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

    val result: StateFlow<Result<User>> = MutableStateFlow(Result.Initial)

    fun changePassword(password: String, newPassword: String) {
        userStore.getUsername().flatMapConcat { username ->
            userRepository.changePassword(username, password, newPassword)
        }.notify(stateFlow = result())
            .launchIn(viewModelScope)
    }
}
