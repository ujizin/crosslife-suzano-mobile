package br.com.crosslife.domain.repositories

import br.com.crosslife.domain.models.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchNotices(sentence: String? = null): Flow<List<Notice>>
}