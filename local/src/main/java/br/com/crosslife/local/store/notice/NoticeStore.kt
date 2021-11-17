package br.com.crosslife.local.store.notice

import br.com.crosslife.local.entities.Notice

interface NoticeStore {
    suspend fun getNotices(): List<Notice>
    suspend fun insertNotices(notices: Collection<Notice>)
}
