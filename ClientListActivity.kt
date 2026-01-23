package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ClientListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)

        val listView = findViewById<ListView>(R.id.listViewClients)

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ClientData.clientList
        )
    }
}
