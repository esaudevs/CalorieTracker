package com.esaudev.core.util

import android.content.Context

sealed class UiText {
    data class DynamicString(val text: String): UiText()
    data class StringResource(val redId: Int): UiText()

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> text
            is StringResource -> context.getString(redId)
        }
    }
}