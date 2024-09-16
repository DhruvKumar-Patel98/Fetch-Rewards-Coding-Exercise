package com.example.fetchrewardscodingexercise.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardscodingexercise.controller.ItemController
import com.example.fetchrewardscodingexercise.databinding.ActivityMainBinding
import com.example.fetchrewardscodingexercise.model.Item

class MainActivity : AppCompatActivity(), ItemController.ItemCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemController: ItemController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemAdapter = ItemAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
        }

        itemController = ItemController(this)
        itemController.fetchItems()
    }

    override fun onItemsFetched(items: List<Item>) {
        runOnUiThread {
            val groupedItems = items.groupBy { it.listId }
                .toSortedMap()
                .flatMap { (_, items) -> items.sortedBy { it.name } }
            itemAdapter.submitList(groupedItems)
        }
    }

    override fun onError(error: String) {
        runOnUiThread {
            Toast.makeText(this@MainActivity, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }
}