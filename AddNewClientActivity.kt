package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddNewClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_client)

        val etName = findViewById<EditText>(R.id.etName)
        val etContact = findViewById<EditText>(R.id.etContact)
        val etPan = findViewById<EditText>(R.id.etPan)
        val etGst = findViewById<EditText>(R.id.etGst)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etBusiness = findViewById<EditText>(R.id.etBusiness)
        val spinner = findViewById<Spinner>(R.id.spinnerCategory)
        val btnSave = findViewById<Button>(R.id.btnSaveClient)

        val categories = arrayOf("Individual", "Business", "SME")
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )

        btnSave.setOnClickListener {

            val name = etName.text.toString()
            val contact = etContact.text.toString()
            val pan = etPan.text.toString()
            val gst = etGst.text.toString()
            val address = etAddress.text.toString()
            val business = etBusiness.text.toString()

            if (name.isEmpty()) {
                etName.error = "Enter client name"
                return@setOnClickListener
            }

            if (contact.length != 10) {
                etContact.error = "Enter 10 digit contact number"
                return@setOnClickListener
            }

            if (pan.length != 10) {
                etPan.error = "PAN must be 10 characters"
                return@setOnClickListener
            }

            if (!pan[0].isLetter() ||
                !pan[1].isLetter() ||
                !pan[2].isLetter() ||
                !pan[3].isLetter() ||
                !pan[4].isLetter()
            ) {
                etPan.error = "First 5 must be letters"
                return@setOnClickListener
            }

            if (!pan[5].isDigit() ||
                !pan[6].isDigit() ||
                !pan[7].isDigit() ||
                !pan[8].isDigit()
            ) {
                etPan.error = "Next 4 must be digits"
                return@setOnClickListener
            }

            if (!pan[9].isLetter()) {
                etPan.error = "Last must be letter"
                return@setOnClickListener
            }

            if (gst.isEmpty()) {
                etGst.error = "Enter GST number"
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                etAddress.error = "Enter address"
                return@setOnClickListener
            }

            if (business.isEmpty()) {
                etBusiness.error = "Enter business details"
                return@setOnClickListener
            }

            ClientData.clientList.add(name)

            Toast.makeText(this, "Client Saved Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
