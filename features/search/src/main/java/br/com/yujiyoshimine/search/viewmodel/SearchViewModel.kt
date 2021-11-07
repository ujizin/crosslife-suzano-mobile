package br.com.yujiyoshimine.search.viewmodel

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weeklyTrainRepository: WeeklyTrainRepository,
    private val noticeRepository: NoticeRepository,
) : ViewModel(), ViewModelExtensions {

    private var job: Job? = null

    val notices: StateFlow<Result<List<Notice>>> = MutableStateFlow(Result.Initial)
    val weeklyTrains: StateFlow<Result<List<WeeklyTrain>>> = MutableStateFlow(Result.Initial)

    init {
        getWeeklyTrains()
        getNotices()
    }

    fun getWeeklyTrains() {
        weeklyTrainRepository.fetchWeeklyTrains()
            .notify(viewModelScope, weeklyTrains())
    }

    fun getNotices(sentence: String? = null) {
        job?.cancel()
        noticeRepository.fetchNotices(sentence)
            .onSentenceDelay(sentence)
            .notify(viewModelScope, notices())
            .apply { job = this }
    }
}
