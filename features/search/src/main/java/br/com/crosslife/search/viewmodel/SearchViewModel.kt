package br.com.crosslife.search.viewmodel

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
