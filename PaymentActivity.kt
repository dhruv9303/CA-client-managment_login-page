package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val rbUpi = findViewById<RadioButton>(R.id.rbUpi)
        val rbNet = findViewById<RadioButton>(R.id.rbNet)
        val rbCard = findViewById<RadioButton>(R.id.rbCard)
        val btnPay = findViewById<Button>(R.id.btnPay)

        val position = intent.getIntExtra("pos", -1)
        val amountFromInvoice = intent.getStringExtra("amount")

        etAmount.setText(amountFromInvoice)

        btnPay.setOnClickListener {

            val amount = etAmount.text.toString()

            if (amount.isEmpty()) {
                etAmount.error = "Enter amount"
                return@setOnClickListener
            }

            if (position != -1) {
                InvoiceData.statusList[position] = "Paid"
            }

            Toast.makeText(
                this,
                "Payment Successful\n₹$amount",
                Toast.LENGTH_LONG
            ).show()

            finish()
        }
    }
}
