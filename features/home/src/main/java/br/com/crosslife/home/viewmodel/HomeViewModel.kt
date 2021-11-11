package br.com.crosslife.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.notify
import br.com.crosslife.commons.extensions.onSentenceDelay
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.Notice
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.domain.repository.NoticeRepository
import br.com.crosslife.domain.repository.WeeklyTrainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weeklyTrainRepository: WeeklyTrainRepository,
    private val noticeRepository: NoticeRepository,
) : ViewModel(), ViewModelExtensions {

    private var job: Job? = null

    val isRefresh: StateFlow<Boolean> = MutableStateFlow(false)

    val weeklyTrains: StateFlow<Result<List<WeeklyTrain>>> = MutableStateFlow(Result.Initial)

    val notices: StateFlow<Result<List<Notice>>> = MutableStateFlow(Result.Initial)

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

    fun getWeeklyTrains() {
        weeklyTrainRepository.fetchWeeklyTrains()
            .notify(viewModelScope, weeklyTrains())
    }

    fun getNotices(text: String? = null) {
        job?.cancel()
        noticeRepository.fetchNotices(text)
            .onSentenceDelay(text)
            .notify(viewModelScope, notices())
            .apply { job = this }
    }

    fun refresh() {
        isRefresh().value = true
    }
}
