package com.example.realdatabasefirebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private var email: EditText? = null
    private var save: Button? = null
    private var delete: Button? = null
    private var myDB: DatabaseReference? = null
    private val TOKEN = "TOKEN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClick()
    }

    private fun onClick() {
        email = findViewById(R.id.email)
        save = findViewById(R.id.save)
        delete = findViewById(R.id.delete)
        myDB = FirebaseDatabase.getInstance().getReference(TOKEN)
    }

    fun onClickSave(view: View?) {
        val userDatabase = myDB!!.push()
        val token = email!!.text.toString()
        val newToken = Token(token)
        userDatabase.setValue(newToken)
    }

    fun onClickRead(view: View?) {
        val intent = Intent(this@MainActivity, ActivityRead::class.java)
        startActivity(intent)
    }
}