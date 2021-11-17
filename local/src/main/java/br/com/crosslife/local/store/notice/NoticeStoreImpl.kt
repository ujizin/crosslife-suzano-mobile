package br.com.crosslife.local.store.notice

import br.com.crosslife.local.dao.NoticeDao
import br.com.crosslife.local.entities.Notice

class NoticeStoreImpl(
    private val noticeDao: NoticeDao,
) : NoticeStore {

    override suspend fun getNotices() = noticeDao.getAll()

    override suspend fun insertNotices(notices: Collection<Notice>) {
        noticeDao.insertAll(notices)
    }
}
