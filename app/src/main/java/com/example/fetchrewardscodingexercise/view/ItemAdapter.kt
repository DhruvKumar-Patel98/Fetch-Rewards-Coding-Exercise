package com.example.fetchrewardscodingexercise.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardscodingexercise.databinding.ItemRowBinding
import com.example.fetchrewardscodingexercise.model.Item


class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val itemList = mutableListOf<Item>()

    fun submitList(items: List<Item>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class ItemViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.itemName.text = "Name: ${item.name ?: "Unknown"}"
            binding.listId.text = "List ID: ${item.listId}"
        }
    }
}
