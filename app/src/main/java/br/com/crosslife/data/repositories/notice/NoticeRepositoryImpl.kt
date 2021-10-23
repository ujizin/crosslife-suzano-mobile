package br.com.crosslife.data.repositories.notice

import br.com.crosslife.core.network.services.NoticeService
import br.com.crosslife.domain.models.Notice
import br.com.crosslife.domain.repositories.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoticeRepositoryImpl(private val noticeService: NoticeService) : NoticeRepository {

    override fun fetchNotices(sentence: String?): Flow<List<Notice>> = flow {
        val notices = noticeService.fetchNotices(sentence).toDomain()
        emit(notices)
    }
}
