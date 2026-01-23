package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class InvoiceListActivity : AppCompatActivity() {

    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_list)

        val btnAdd = findViewById<Button>(R.id.btnAddInvoice)
        listView = findViewById(R.id.listInvoices)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddInvoiceActivity::class.java))
        }

        loadData()

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("pos", position)
            startActivity(intent)
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val status = InvoiceData.statusList[position]

            if (status == "Paid") {
                Toast.makeText(this, "Already Paid", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Reminder sent via Email & SMS",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val displayList = ArrayList<String>()

        for (i in InvoiceData.invoiceList.indices) {
            displayList.add(
                InvoiceData.invoiceList[i] + " | Status: " + InvoiceData.statusList[i]
            )
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            displayList
        )
        listView.adapter = adapter
    }
}
