package br.com.crosslife.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.notify
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.DetailProfile
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val profileState: StateFlow<Result<DetailProfile>> = MutableStateFlow(Result.Initial)

    init {
        getDetailProfile()
    }

    fun getDetailProfile() {
        userRepository.fetchDetailProfile()
            .notify(viewModelScope, profileState())
    }
}
