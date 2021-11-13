package br.com.crosslife.data.repositories.notice

import br.com.crosslife.data.repositories.networkFlow
import br.com.crosslife.domain.model.Notice
import br.com.crosslife.domain.repository.NoticeRepository
import br.com.crosslife.network.services.NoticeService
import kotlinx.coroutines.flow.Flow

internal class NoticeRepositoryImpl(private val noticeService: NoticeService) : NoticeRepository {

    override fun fetchNotices(sentence: String?): Flow<List<Notice>> = networkFlow {
        val notices = noticeService.fetchNotices(sentence).toDomain()
        emit(notices)
    }
}
