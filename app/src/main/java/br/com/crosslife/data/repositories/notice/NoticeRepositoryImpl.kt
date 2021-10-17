package br.com.crosslife.data.repositories.notice

import br.com.crosslife.core.network.services.NoticeService
import br.com.crosslife.domain.models.Notice
import br.com.crosslife.domain.repositories.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoticeRepositoryImpl(private val noticeService: NoticeService) : NoticeRepository {

    private var notices: List<Notice> = listOf()

    override fun fetchNotices(sentence: String?): Flow<List<Notice>> = flow {
        val notices = when {
            !sentence.isNullOrBlank() -> noticeService.fetchNotices(sentence).toDomain()
            notices.isEmpty() -> noticeService.fetchNotices().toDomain().also { list ->
                notices = list
            }
            else -> notices
        }
        emit(notices)
    }
}
