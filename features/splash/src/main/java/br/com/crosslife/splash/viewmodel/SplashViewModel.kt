package br.com.crosslife.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(SplashStateUi.Initial)
    val isAuthenticated: StateFlow<SplashStateUi> get() = _isAuthenticated

    init {
        userRepository.getToken()
            .onStart {
                delay(SPLASH_DELAY)
            }
            .onEach {
                _isAuthenticated.value = SplashStateUi.getResult(it)
            }
            .launchIn(viewModelScope)
    }


    companion object {
        private const val SPLASH_DELAY = 1000L
    }
}

enum class SplashStateUi {
    Initial, Authenticated, NotAuthenticated;

    companion object {
        fun getResult(token: String) =
            if (token.isNotBlank()) Authenticated else NotAuthenticated

    }
}
