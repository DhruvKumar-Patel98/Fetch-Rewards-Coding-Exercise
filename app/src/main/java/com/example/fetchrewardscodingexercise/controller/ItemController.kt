package com.example.fetchrewardscodingexercise.controller

import com.example.fetchrewardscodingexercise.model.Item
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class ItemController(private val callback: ItemCallback) {

    interface ItemCallback {
        fun onItemsFetched(items: List<Item>)
        fun onError(error: String)
    }

    fun fetchItems() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://fetch-hiring.s3.amazonaws.com/hiring.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError(e.message ?: "Unknown error")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { body ->
                    try {
                        val items = Gson().fromJson(body.string(), Array<Item>::class.java).toList()
                        // Filter and sort items
                        val filteredItems = items.filter { !it.name.isNullOrBlank() }
                            .sortedWith(compareBy({ it.listId }, { it.name }))
                        callback.onItemsFetched(filteredItems)
                    } catch (e: Exception) {
                        callback.onError(e.message ?: "Parsing error")
                    }
                } ?: callback.onError("Response body is null")
            }
        })
    }
}