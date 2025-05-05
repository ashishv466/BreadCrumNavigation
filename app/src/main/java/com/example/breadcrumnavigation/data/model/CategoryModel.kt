package com.example.breadcrumnavigation.data.model

data class CategoryResponse(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
    val isSuperCategory: Int? = null,
    val subcategories: List<SubCategory>? = null
)

data class SubCategory(
    val id: Int,
    val name: String,
    val isCategory: Int? = null,
    val items: List<Item>? = null
)

data class Item(
    val id: Int? = null,
    val name: String,
    val price: Int? = null,
    val isCategory: Int? = null,
    val items: List<Item>? = null
)
