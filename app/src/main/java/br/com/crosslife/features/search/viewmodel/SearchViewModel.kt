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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weeklyTrainRepository: WeeklyTrainRepository,
    private val noticeRepository: NoticeRepository,
) : ViewModel(), ViewModelExtensions {

    val notices: StateFlow<Result<List<Notice>>> = MutableStateFlow(Result.Initial)
    val weeklyTrains: StateFlow<Result<List<WeeklyTrain>>> = MutableStateFlow(Result.Initial)

    init {
        getWeeklyTrains()
        getNotices()
    }

    private fun getWeeklyTrains() {
        weeklyTrainRepository.fetchWeeklyTrains()
            .notify(viewModelScope, weeklyTrains())
    }

    fun getNotices(sentence: String? = null) {
        noticeRepository.fetchNotices(sentence)
            .debounce(if (sentence != null) DEBOUNCE_DELAY else NO_DELAY)
            .notify(viewModelScope, notices())
    }

    companion object {
        private const val DEBOUNCE_DELAY = 300L
        private const val NO_DELAY = 0L
    }
}
