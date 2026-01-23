package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class UploadDocumentActivity : AppCompatActivity() {

    private var selectedUri: Uri? = null
    private val docList = ArrayList<String>()
    private val uriList = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_document)

        val etName = findViewById<EditText>(R.id.etSearchDoc)
        val etExpiry = findViewById<EditText>(R.id.etExpiryDate)
        val spinner = findViewById<Spinner>(R.id.spnCategory)
        val btnChoose = findViewById<Button>(R.id.btnChooseFile)
        val btnUpload = findViewById<Button>(R.id.btnUpload)
        val tvFile = findViewById<TextView>(R.id.tvFileName)
        val listView = findViewById<ListView>(R.id.listViewDocs)

        val categories = arrayOf("GST Filing", "IT Return", "Audit Report", "Financial Statement")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, docList)
        listView.adapter = adapter

        btnChoose.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, 1)
        }

        btnUpload.setOnClickListener {

            val name = etName.text.toString()
            val expiry = etExpiry.text.toString()
            val category = spinner.selectedItem.toString()

            if (name.isEmpty()) {
                etName.error = "Enter document name"
                return@setOnClickListener
            }

            if (expiry.isEmpty()) {
                etExpiry.error = "Enter expiry date"
                return@setOnClickListener
            }

            if (selectedUri == null) {
                Toast.makeText(this, "Choose file", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = "$name | $category | $expiry"
            docList.add(data)
            uriList.add(selectedUri!!)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

            etName.setText("")
            etExpiry.setText("")
            tvFile.text = "No file selected"
            selectedUri = null
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val uri = uriList[position]
            val item = docList[position]
            val parts = item.split("|")
            val expiry = parts[2].trim()

            checkExpiry(expiry)

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "*/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            selectedUri = data?.data
            val tvFile = findViewById<TextView>(R.id.tvFileName)
            tvFile.text = "File Selected"
        }
    }

    private fun checkExpiry(expiry: String) {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val expiryDate = sdf.parse(expiry)
            val today = Date()

            if (expiryDate != null && expiryDate.before(today)) {
                Toast.makeText(this, "Document Expired", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Document Valid", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show()
        }
    }
}
