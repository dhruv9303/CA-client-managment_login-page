package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddInvoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_invoice)

        val etClient = findViewById<EditText>(R.id.etClient)
        val etService = findViewById<EditText>(R.id.etService)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etDueDate = findViewById<EditText>(R.id.etDueDate)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)

        btnGenerate.setOnClickListener {

            val client = etClient.text.toString()
            val service = etService.text.toString()
            val amount = etAmount.text.toString()
            val due = etDueDate.text.toString()

            if (client.isEmpty() || service.isEmpty() || amount.isEmpty() || due.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val invoice = "$client | $service | ₹$amount | Due: $due"
            InvoiceData.invoiceList.add(invoice)
            InvoiceData.statusList.add("Pending")

            val pos = InvoiceData.invoiceList.size - 1

            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("pos", pos)
            intent.putExtra("amount", amount)
            startActivity(intent)
            finish()
        }
    }
}
