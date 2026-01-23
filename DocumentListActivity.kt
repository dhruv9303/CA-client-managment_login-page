package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DocumentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_list)

        val listView = findViewById<ListView>(R.id.listDocs)

        val demoDocs = listOf("GST File", "IT Return", "Audit Report")

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            demoDocs
        )
    }
}
