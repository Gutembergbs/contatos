package com.example.contactapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditContactActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtNumber: EditText
    private lateinit var edtEmail: EditText
    private lateinit var db: DatabaseHelper
    private var contactId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        edtName = findViewById(R.id.edtName)
        edtNumber = findViewById(R.id.edtNumber)
        edtEmail = findViewById(R.id.edtEmail)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        db = DatabaseHelper(this)

        contactId = intent.getIntExtra("CONTACT_ID", 0)
        val contact = db.getAllContacts().first { it.id == contactId }

        edtName.setText(contact.name)
        edtNumber.setText(contact.number)
        edtEmail.setText(contact.email)

        btnUpdate.setOnClickListener {
            val updatedContact = Contact(
                id = contactId,
                name = edtName.text.toString(),
                number = edtNumber.text.toString(),
                email = edtEmail.text.toString()
            )

            db.updateContact(updatedContact)
            Toast.makeText(this, "Contato Atualizado!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}