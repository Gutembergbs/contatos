package com.example.contactapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewContactsActivity : AppCompatActivity() {

    private lateinit var listViewContacts: ListView
    private lateinit var db: DatabaseHelper
    private lateinit var contactList: List<Contact>
    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contacts)

        listViewContacts = findViewById(R.id.listViewContacts)
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        db = DatabaseHelper(this)

        loadContacts()

        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString()
            searchContacts(query)
        }
    }

    private fun loadContacts() {
        contactList = db.getAllContacts()
        val adapter = ContactAdapter(this, contactList)
        listViewContacts.adapter = adapter
    }

    private fun searchContacts(query: String) {
        contactList = db.searchContacts(query)
        val adapter = ContactAdapter(this, contactList)
        listViewContacts.adapter = adapter
    }

    private inner class ContactAdapter(context: Context, private val contacts: List<Contact>) : ArrayAdapter<Contact>(context, 0, contacts) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)

            val contact = contacts[position]

            val tvName = view.findViewById<TextView>(R.id.tvContactName)
            val tvNumber = view.findViewById<TextView>(R.id.tvContactNumber)
            val tvEmail = view.findViewById<TextView>(R.id.tvContactEmail)
            val btnEdit = view.findViewById<Button>(R.id.btnEdit)
            val btnDelete = view.findViewById<Button>(R.id.btnDelete)

            tvName.text = contact.name
            tvNumber.text = contact.number
            tvEmail.text = contact.email

            btnEdit.setOnClickListener {
                val intent = Intent(context, EditContactActivity::class.java).apply {
                    putExtra("CONTACT_ID", contact.id)
                }
                context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                db.deleteContact(contact.id)
                loadContacts()  // Atualiza a lista após a exclusão
            }

            return view
        }
    }
}
