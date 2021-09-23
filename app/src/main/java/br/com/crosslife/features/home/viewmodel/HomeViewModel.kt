package br.com.crosslife.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.Notice
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.domain.repositories.NoticeRepository
import br.com.crosslife.domain.repositories.WeeklyTrainRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weeklyTrainRepository: WeeklyTrainRepository,
    private val noticeRepository: NoticeRepository,
) : ViewModel(), ViewModelExtensions {

    val isRefresh: StateFlow<Boolean> = MutableStateFlow(false)

    val weeklyTrains: StateFlow<Result<List<WeeklyTrain>>> = MutableStateFlow(Result.Initial)

    val notices: StateFlow<Result<Notice>> = MutableStateFlow(Result.Initial)

    init {
        viewModelScope.launch {
            isRefresh.collect { isRefreshing ->
                if (isRefreshing) {
                    flowOf(getWeeklyTrains(), getNotices())
                        .onCompletion { isRefresh().value = false }
                        .collect()
                }
            }
        }

        getWeeklyTrains()
        getNotices()
    }

    private fun getWeeklyTrains() = weeklyTrainRepository.fetchWeeklyTrains()
        .notify(viewModelScope, weeklyTrains())

    private fun getNotices() = noticeRepository.fetchNotices()
        .notify(viewModelScope, notices())

    fun refresh() {
        isRefresh().value = true
    }
}
