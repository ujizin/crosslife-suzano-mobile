package br.com.crosslife.data.repositories.notice

import br.com.crosslife.domain.model.Notice
import br.com.crosslife.network.dto.NoticeDTO
import br.com.crosslife.local.entities.Notice as LocalNotice

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

internal fun List<br.com.crosslife.local.entities.Notice>.localToDomain(): List<Notice> =
    map { it.localToDomain() }

internal fun br.com.crosslife.local.entities.Notice.localToDomain() =
    Notice(
        id,
        title,
        subtitle,
        content,
        category,
        author,
        date,
        imageUrl
    )

internal fun List<Notice>.toLocal(): Collection<LocalNotice> = map { it.domainToLocal() }

internal fun Notice.domainToLocal() = LocalNotice(
    id,
    title,
    subtitle,
    content,
    category,
    author,
    date,
    imageUrl
)