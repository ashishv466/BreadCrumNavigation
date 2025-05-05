package com.example.breadcrumnavigation

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breadcrumnavigation.data.model.Category
import com.example.breadcrumnavigation.data.model.Item
import com.example.breadcrumnavigation.data.model.SubCategory
import com.example.breadcrumnavigation.ui.adapter.CategoryAdapter
import com.example.breadcrumnavigation.ui.viewmodel.CategoryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var breadcrumbLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        adapter = CategoryAdapter {
            handleItemClick(it)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        breadcrumbLayout = findViewById(R.id.breadcrumbLayout)

        viewModel.currentItems.observe(this) {
            adapter.submitList(it)
        }

        viewModel.breadcrumb.observe(this) {
            updateBreadcrumb(it)
        }
    }

    private fun handleItemClick(item: Any) {
        when (item) {
            is Category -> item.subcategories?.let {
                viewModel.navigateTo(it.map { sc -> sc as Any }, item.name)
            }
            is SubCategory -> item.items?.let {
                viewModel.navigateTo(it.map { i -> i as Any }, item.name)
            }
            is Item -> item.items?.let {
                viewModel.navigateTo(it.map { i -> i as Any }, item.name)
            }
        }
    }

    private fun updateBreadcrumb(breadcrumb: List<String>) {
        breadcrumbLayout.removeAllViews()
        breadcrumb.forEachIndexed { index, name ->
            val tv = TextView(this).apply {
                text = name
                setPadding(8, 4, 8, 4)
                setOnClickListener { viewModel.goBackTo(index) }
            }
            breadcrumbLayout.addView(tv)
            if (index < breadcrumb.size - 1) {
                breadcrumbLayout.addView(TextView(this).apply { text = " > " })
            }
        }
    }
}