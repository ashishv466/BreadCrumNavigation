package com.example.breadcrumnavigation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.breadcrumnavigation.data.model.Category
import com.example.breadcrumnavigation.data.model.CategoryResponse
import com.example.breadcrumnavigation.utils.JSONUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Stack

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentItems = MutableLiveData<List<Any>>() // Item or SubCategory
    val currentItems: LiveData<List<Any>> = _currentItems

    val breadcrumb = MutableLiveData<List<String>>()

    private val navigationStack = Stack<List<Any>>()
    private val breadcrumbStack = Stack<String>()

    private lateinit var rootCategories: List<Category>

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = JSONUtil.getJSONFromAssets(getApplication(), "categories.json")
            val categoryResponse = Gson().fromJson(json, CategoryResponse::class.java)
            rootCategories = categoryResponse.categories

            withContext(Dispatchers.Main) {
                navigateTo(rootCategories.map { it as Any }, "Home")
            }
        }
    }

    fun navigateTo(items: List<Any>, title: String) {
        navigationStack.push(items)
        breadcrumbStack.push(title)
        _currentItems.value = items
        breadcrumb.value = breadcrumbStack.toList()
    }

    fun goBackTo(index: Int) {
        while (breadcrumbStack.size > index + 1) {
            breadcrumbStack.pop()
            navigationStack.pop()
        }
        _currentItems.value = navigationStack.peek()
        breadcrumb.value = breadcrumbStack.toList()
    }
}