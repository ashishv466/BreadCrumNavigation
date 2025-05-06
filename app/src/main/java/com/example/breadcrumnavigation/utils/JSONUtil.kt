package com.example.breadcrumnavigation.utils

import android.content.Context
import com.example.breadcrumnavigation.R

object JSONUtil {
    fun getJSONFromAssets(context: Context): String {

        val jsonString = context.resources.openRawResource(R.raw.categories)
            .bufferedReader().use { it.readText() }

        return jsonString
    }
}