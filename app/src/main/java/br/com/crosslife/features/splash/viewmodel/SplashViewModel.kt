package br.com.crosslife.features.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.domain.preferences.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    userStore: UserStore,
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(SplashResult.Initial)
    val isAuthenticated: StateFlow<SplashResult> get() = _isAuthenticated

    init {
        userStore.getToken()
            .onStart {
                delay(SPLASH_DELAY)
            }
            .onEach {
                _isAuthenticated.value = SplashResult.getResult(it)
            }
            .launchIn(viewModelScope)
    }


    companion object {
        private const val SPLASH_DELAY = 1000L
    }
}

enum class SplashResult {
    Initial, Authenticated, NotAuthenticated;

    companion object {
        fun getResult(token: String) =
            if (token.isNotBlank()) Authenticated else NotAuthenticated

    }
}
