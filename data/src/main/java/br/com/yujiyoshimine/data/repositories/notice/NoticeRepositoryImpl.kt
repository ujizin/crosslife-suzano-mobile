package br.com.yujiyoshimine.data.repositories.notice

import br.com.yujiyoshimine.domain.model.Notice
import br.com.yujiyoshimine.domain.repository.NoticeRepository
import br.com.yujiyoshimine.network.services.NoticeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoticeRepositoryImpl(private val noticeService: NoticeService) : NoticeRepository {

    override fun fetchNotices(sentence: String?): Flow<List<Notice>> = flow {
        val notices = noticeService.fetchNotices(sentence).toDomain()
        emit(notices)
    }
}
