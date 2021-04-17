package com.example.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_checkin.*

class checkin : AppCompatActivity() {

    private  lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        var database = FirebaseDatabase.getInstance().reference

        button2.setOnClickListener {
            var username = editTextTextPersonName2.text.toString()
            var numberofdays = editTextTextPersonName4.text.toString()
            var checkintime = editTextTextPersonName5.text.toString()
            var checkinnumber = editTextTextPersonName6.text.toString()

            database.child(checkinnumber.toString()).setValue(Check(username, numberofdays, checkintime))
            Toast.makeText(this, "Check In Successful", Toast.LENGTH_LONG).show()
        }


        /*var getdata = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children){
                    var

                }
            }
        }*/



    }






}