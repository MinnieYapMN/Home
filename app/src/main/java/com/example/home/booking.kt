package com.example.home

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlinx.android.synthetic.main.activity_booking.*

class booking : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var referance: DatabaseReference
    var formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)


    @RequiresApi(Build.VERSION_CODES.O)
    val getDate = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    val year = getDate.get(Calendar.YEAR)

    @RequiresApi(Build.VERSION_CODES.N)
    val month = getDate.get(Calendar.MONTH)

    @RequiresApi(Build.VERSION_CODES.N)
    val day = getDate.get(Calendar.DAY_OF_MONTH)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        database = FirebaseDatabase.getInstance();
        referance = database.getReference("Users")
        Cin.setOnClickListener {
            val datepicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                    Cin.setText(String.format("%02d-%02d-%02d", myear, mmonth + 1, mdayOfMonth))
                }, year, month, day
            )

            datepicker.show()
        }
        Cout.setOnClickListener {
            val datepicker2 = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->

                    Cout.setText(String.format("%02d-%02d-%02d", myear, mmonth + 1, mdayOfMonth))

                }, year, month, day
            )

            datepicker2.show()
        }

        calTotal.setOnClickListener {
            val start = LocalDate.parse(Cin.text)
            val end = LocalDate.parse(Cout.text)
            val night = start.until(end, ChronoUnit.DAYS)

            testDays.text = "${night.toString()} night"
            totalCost.text = "RM${night * 100}"
        }

        btn_send.setOnClickListener {
            sendData()
        }
        btn_getdata.setOnClickListener {
            startActivity(Intent(applicationContext, Getdata::class.java))
        }
    }

    private fun sendData() {

        var cName = custName.text.toString().trim()
        var pNo = phoneNo.text.toString().trim().toInt()
        var numberOfGuests = numOfG.text.toString().toInt()
        var cRequest = request.text.toString().trim()
        var testD = testDays.text.toString().trim()
        var totalC = totalCost.text.toString().trim()

        if (cName.isNotEmpty() && cRequest.isNotEmpty()) {
            var model = DatabaseModel(cName, pNo, numberOfGuests, cRequest, testD, totalC)
            var id = referance.push().key

            referance.child(id!!).setValue(model)
            custName.setText("")
            phoneNo.setText("")
            numOfG.setText("")
            request.setText("")
            testDays.setText("")
            totalCost.setText("")

        } else {
            Toast.makeText(applicationContext, "All Field Required", Toast.LENGTH_LONG).show()
        }
    }

}