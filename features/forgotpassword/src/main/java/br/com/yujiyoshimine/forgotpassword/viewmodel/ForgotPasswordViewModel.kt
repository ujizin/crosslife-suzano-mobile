package br.com.yujiyoshimine.forgotpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.notify
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.repository.UserRepository
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