package br.com.crosslife.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Train(
    @PrimaryKey
    val id: Int,
)