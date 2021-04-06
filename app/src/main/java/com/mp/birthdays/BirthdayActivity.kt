package com.mp.birthdays

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_birthday.*
import com.mp.birthdays.database.DatabaseHelper
import java.util.*

class BirthdayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

    }

    fun save(v: View){
        val selectedDate = datePicker.dayOfMonth
        val selectedMonth = datePicker.month
        val name = name.text.toString()

        val list = listOf("Jan", "Feb", "Mar", "Apr",
        "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec")

        val birthdayDB = list[selectedMonth]+" "+selectedDate.toString()

        var dbManager = DatabaseHelper(this)
        var values = ContentValues()
        values.put("Name", name)
        values.put("Birthday", birthdayDB)

        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        val ID = dbManager.insert(name, birthdayDB, color)


        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
