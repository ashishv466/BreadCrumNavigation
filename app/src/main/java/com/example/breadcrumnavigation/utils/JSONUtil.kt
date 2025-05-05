package com.example.breadcrumnavigation.utils

import android.content.Context

object JSONUtil {
    fun getJSONFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}