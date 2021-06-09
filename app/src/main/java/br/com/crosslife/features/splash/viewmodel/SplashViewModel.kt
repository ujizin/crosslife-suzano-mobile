package br.com.crosslife.features.splash.viewmodel

import androidx.lifecycle.ViewModel
import br.com.crosslife.core.local.datapreferences.DataPreferences
import br.com.crosslife.data.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: DataPreferences
) : ViewModel() {

}
