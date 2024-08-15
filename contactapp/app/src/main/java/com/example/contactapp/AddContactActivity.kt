package com.example.contactapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var edtEmail: EditText
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        edtName = findViewById(R.id.edtName)
        edtNumber = findViewById(R.id.edtNumber)
        edtEmail = findViewById(R.id.edtEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)

        db = DatabaseHelper(this)

        btnSave.setOnClickListener {
            val name = edtName.text.toString()
            val number = edtNumber.text.toString()
            val email = edtEmail.text.toString()

            db.addContact(name, number, email)
            Toast.makeText(this, "Contato Salvo!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
