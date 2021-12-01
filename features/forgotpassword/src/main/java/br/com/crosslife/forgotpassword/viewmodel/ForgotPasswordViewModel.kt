package br.com.crosslife.forgotpassword.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.notify
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.manager.PushManager
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val pushManager: PushManager,
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val forgotPassword: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun sendForgotNotification(context: Context, title: String, message: String) {
        pushManager.sendNotification(context, title, message)
    }

    fun fetchForgotPassword(username: String) {
        userRepository.fetchPassword(username)
            .notify(viewModelScope, forgotPassword())
    }
}