package br.com.yujiyoshimine.domain.repository

import br.com.yujiyoshimine.domain.model.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchNotices(sentence: String? = null): Flow<List<Notice>>
}