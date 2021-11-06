package br.com.yujiyoshimine.data.repositories.notice

import br.com.yujiyoshimine.domain.model.Notice
import br.com.yujiyoshimine.network.dto.NoticeDTO

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