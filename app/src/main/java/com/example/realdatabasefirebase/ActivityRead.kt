package com.example.realdatabasefirebase

import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.widget.ListView
import com.example.realdatabasefirebase.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.util.ArrayList

class ActivityRead : AppCompatActivity() {
    private var listView: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var listData: MutableList<String>? = null
    private var mDB: DatabaseReference? = null
    private val SmartBrotherTechBot = "TOKEN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        init()
        dataFormDb
    }

    private fun init() {
        listView = findViewById(R.id.listView)
        listData = ArrayList()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData!!)
        listView!!.adapter = arrayAdapter
        mDB = FirebaseDatabase.getInstance().getReference(SmartBrotherTechBot)
    }

    private val dataFormDb: Unit
        get() {
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (listData!!.size > 0) listData!!.clear()

                    for (ds in dataSnapshot.children) {
                        val user = ds.getValue(Token::class.java)!!
                        listData!!.add(user.token)

                    }
                    arrayAdapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }
            mDB!!.addValueEventListener(valueEventListener)
        }
}