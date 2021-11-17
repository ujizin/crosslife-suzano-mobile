package br.com.crosslife.data.repositories.notice

import br.com.crosslife.data.repositories.networkFlow
import br.com.crosslife.domain.model.Notice
import br.com.crosslife.domain.repository.NoticeRepository
import br.com.crosslife.local.store.notice.NoticeStore
import br.com.crosslife.network.services.NoticeService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

internal class NoticeRepositoryImpl(
    private val noticeService: NoticeService,
    private val noticeStore: NoticeStore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : NoticeRepository {

    override fun fetchNotices(sentence: String?): Flow<List<Notice>> = networkFlow {
        val notices = noticeService.fetchNotices(sentence).toDomain()
        noticeStore.insertNotices(notices.toLocal())
        emit(notices)
    }.catch {
        // TODO filter notices with sentence parameter
        val notices = noticeStore.getNotices()
        check(notices.isNotEmpty()) { throw it }
        emit(notices.localToDomain())
    }.flowOn(dispatcher)
}
