package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val firstName = findViewById<EditText>(R.id.etFirstName)
        val email = findViewById<EditText>(R.id.etEmail)
        val mobile = findViewById<EditText>(R.id.etMobile)
        val password = findViewById<EditText>(R.id.etPassword)
        val confirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val showPass = findViewById<CheckBox>(R.id.cbShowPassword)
        val registerBtn = findViewById<Button>(R.id.btnRegister)

        showPass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.inputType = android.text.InputType.TYPE_CLASS_TEXT
                confirmPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT
            } else {
                password.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                confirmPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            password.setSelection(password.text.length)
            confirmPassword.setSelection(confirmPassword.text.length)
        }

        registerBtn.setOnClickListener {

            val emailText = email.text.toString()
            val pass = password.text.toString()
            val cPass = confirmPassword.text.toString()

            if (firstName.text.isEmpty()) {
                firstName.error = "Enter first name"
                return@setOnClickListener
            }

            if (emailText.isEmpty() ||
                !emailText.contains("@") ||
                !emailText.contains(".") ||
                emailText.contains(" ")
            ) {
                email.error = "Email must contain @ and gmail.com"
                return@setOnClickListener
            }

            if (mobile.text.length != 10) {
                mobile.error = "Enter 10 digit mobile number"
                return@setOnClickListener
            }

            var validPassword = true

            if (pass.length < 6) validPassword = false
            if (!pass[0].isUpperCase()) validPassword = false

            val special = "!@#$%^&*"
            var found = false
            for (c in pass) {
                if (special.contains(c)) {
                    found = true
                    break
                }
            }
            if (!found) validPassword = false

            if (!validPassword) {
                password.error =
                    "Password must start with capital and contain special character"
                return@setOnClickListener
            }

            if (pass != cPass) {
                confirmPassword.error = "Password not matching"
                return@setOnClickListener
            }

            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AddNewClientActivity::class.java))
            finish()
        }
    }
}
