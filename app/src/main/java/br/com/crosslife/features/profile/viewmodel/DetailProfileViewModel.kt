package br.com.crosslife.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.domain.models.DetailProfile
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import br.com.crosslife.data.Result
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.extensions.notify
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
): ViewModel(), ViewModelExtensions {

    val profileState: StateFlow<Result<DetailProfile>> = MutableStateFlow(Result.Initial)

    init {
        getDetailProfile()
    }

    private fun getDetailProfile() {
        userRepository.fetchDetailProfile()
            .notify(viewModelScope, profileState())
    }
}
