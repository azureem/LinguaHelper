package com.example.linguahelper.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favouriteTable")

data class Favourites(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val word: String,
    val type: String,
    val fav: Int = 0,
    val defn: String? = null
)