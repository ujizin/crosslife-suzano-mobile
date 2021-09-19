package br.com.crosslife.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.domain.repositories.WeeklyTrainRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weeklyTrainRepository: WeeklyTrainRepository,
) : ViewModel(), ViewModelExtensions {

    val isRefresh: StateFlow<Boolean> = MutableStateFlow(false)

    val weeklyTrains: StateFlow<Result<List<WeeklyTrain>>> = MutableStateFlow(Result.Initial)

    // TODO add notices endpoint
    val notices: StateFlow<Result<WeeklyTrain>> = MutableStateFlow(Result.Initial)

    init {
        viewModelScope.launch {
            isRefresh.collect { isRefreshing ->
                if (isRefreshing) {
                    getWeeklyTrains()
                        .onCompletion { isRefresh().value = false }
                        .collect()
                }
            }
        }

        getWeeklyTrains()
    }

    private fun getWeeklyTrains() = weeklyTrainRepository.fetchWeeklyTrains()
        .notify(viewModelScope, weeklyTrains())

    fun refresh() {
        isRefresh().value = true
    }
}
