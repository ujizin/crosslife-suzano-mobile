package br.com.yujiyoshimine.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.notify
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.domain.model.DetailProfile
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.repository.UserRepository
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

    private fun getDetailProfile() {
        userRepository.fetchDetailProfile()
            .notify(viewModelScope, profileState())
    }
}
