package com.example.contactapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddContact = findViewById<Button>(R.id.btnAddContact)
        val btnViewContacts = findViewById<Button>(R.id.btnViewContacts)

        btnAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        btnViewContacts.setOnClickListener {
            val intent = Intent(this, ViewContactsActivity::class.java)
            startActivity(intent)
        }
    }
}
