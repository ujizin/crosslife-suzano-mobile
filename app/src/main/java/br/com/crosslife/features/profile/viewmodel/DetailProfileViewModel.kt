package br.com.crosslife.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import br.com.crosslife.domain.models.DetailProfile
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import br.com.crosslife.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DetailProfileViewModel : ViewModel(), ViewModelExtensions {

    private val profileState: StateFlow<Result<DetailProfile>> = MutableStateFlow(Result.Initial)
}
