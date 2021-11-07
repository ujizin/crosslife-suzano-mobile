package br.com.yujiyoshimine.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.notify
import br.com.yujiyoshimine.commons.extensions.onSentenceDelay
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.domain.model.Notice
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.model.WeeklyTrain
import br.com.yujiyoshimine.domain.repository.NoticeRepository
import br.com.yujiyoshimine.domain.repository.WeeklyTrainRepository
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
