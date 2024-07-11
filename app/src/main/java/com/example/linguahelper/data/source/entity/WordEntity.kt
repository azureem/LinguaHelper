package com.example.linguahelper.data.source.entity

import androidx.room.Entity

@Entity(tableName = "words", primaryKeys = ["word", "type"])
data class WordEntity(
    val word: String,
    val type: String,
    val sdex: String?,
    val wlen: Int? = null,
    val defn: String? = null
)

// meni tushunishim bo'yicha, biz local baza qilib tayyor bazani asset papkada ochib olyabmiz,
// va undan foydallanish uchun shu entityni yaratib olyabmiz 1/1 copy qilib fieldlarini!!!

// table si AppDatabase ni
