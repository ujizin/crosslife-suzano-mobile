package br.com.crosslife.data.repositories.notice

import br.com.crosslife.core.network.dto.NoticeDTO
import br.com.crosslife.domain.models.Notice

internal fun List<NoticeDTO>.toDomain(): List<Notice> = map { it.toDomain() }

internal fun NoticeDTO.toDomain() = Notice(
    id = id,
    title = title,
    subtitle = subtitle,
    content = content,
    category = category,
    author = author,
    date = date,
    imageUrl = imageUrl
)