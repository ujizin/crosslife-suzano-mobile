package br.com.crosslife.features.search.viewmodel

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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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
        job = viewModelScope.launch {
            noticeRepository.fetchNotices(sentence)
                .onStart { if (!sentence.isNullOrBlank()) delay(DEBOUNCE_DELAY) }
                .notify(stateFlow = notices())
                .collect()
        }
    }

    companion object {
        private const val DEBOUNCE_DELAY = 300L
    }
}
