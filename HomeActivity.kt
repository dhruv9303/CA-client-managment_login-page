package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnClients = findViewById<Button>(R.id.btnClients)
        val btnDocuments = findViewById<Button>(R.id.btnDocuments)
        val btnInvoices = findViewById<Button>(R.id.btnInvoices)

        btnClients.setOnClickListener {
            startActivity(Intent(this, ClientListActivity::class.java))
        }

        btnDocuments.setOnClickListener {
            startActivity(Intent(this, UploadDocumentActivity::class.java))
        }

        btnInvoices.setOnClickListener {
            startActivity(Intent(this, InvoiceListActivity::class.java))
        }
    }
}
