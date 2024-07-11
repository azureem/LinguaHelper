package com.example.linguahelper.data

import com.example.linguahelper.data.model.WordData
import com.example.linguahelper.data.source.entity.WordEntity

object MyMapper {

    fun WordEntity.toData(): WordData {

       return WordData(word = this.word, type = this.type, definition = this.defn ?: "")
    }
}

//bu yerda myMapper huddiki filterlik vazifasni bajarmoqda, enitydan kelgan malumotni faqatgina keraklilarini saralab olyapti
