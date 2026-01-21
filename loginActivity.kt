package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val showPassword = findViewById<CheckBox>(R.id.showPassword)
        val loginBtn = findViewById<Button>(R.id.loginButton)
        val signUp = findViewById<TextView>(R.id.signUpText)

        showPassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            password.setSelection(password.text.length)
        }

        loginBtn.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isEmpty()) {
                username.error = "Enter username"
                return@setOnClickListener
            }

            if (pass.length < 6) {
                password.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            if (user == "dhruv" && pass == "Dhruv@123") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ClientListActivity::class.java))
            } else {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
