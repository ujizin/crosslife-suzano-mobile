package br.com.crosslife.domain.repository

import br.com.crosslife.domain.model.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchNotices(sentence: String? = null): Flow<List<Notice>>
}