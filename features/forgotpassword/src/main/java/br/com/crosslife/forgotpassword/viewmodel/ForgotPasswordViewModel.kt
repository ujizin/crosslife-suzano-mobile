package br.com.crosslife.forgotpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.notify
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val forgotPassword: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun fetchForgotPassword(username: String) {
        userRepository.fetchPassword(username)
            .notify(viewModelScope, forgotPassword())
    }
}