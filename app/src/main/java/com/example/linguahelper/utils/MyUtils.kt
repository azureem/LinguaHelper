package com.example.linguahelper.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.sqlite.db.SimpleSQLiteQuery

fun String.createSpannable(query: String): SpannableString {
    val spannable = SpannableString(this)
    val startIndex = this.indexOf(query)
    val endIndex = startIndex + query.length
    if (startIndex< 0 || endIndex> this.length) return spannable
    spannable.setSpan(
        ForegroundColorSpan(Color.RED),
        startIndex,
        endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}