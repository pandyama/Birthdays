package com.mp.birthdays

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mp.birthdays.adapter.BirthdayAdapter
import com.mp.birthdays.database.DatabaseHelper
import com.mp.birthdays.model.birthday
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var birthdays = ArrayList<birthday>()
    val context = this
    var adapter = BirthdayAdapter(birthdays,context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var DatabaseHelper = DatabaseHelper(this)
        val cursor = DatabaseHelper.getBirthdays()

        while (cursor.moveToNext()) {
            println(cursor.getString(0))
            println(cursor.getString(1))
            println(cursor.getString(2))
            birthdays.add(birthday(cursor.getString(1), cursor.getString(2), cursor.getInt(3)))
        }

        birthdayRV.adapter = adapter
        birthdayRV.layoutManager = LinearLayoutManager(this)
//        birthdayRV.layoutManager = GridLayoutManager(this, 2)
    }

    fun addBirthday(v: View){
        var intent = Intent(this, BirthdayActivity::class.java)
        startActivity(intent)
        finish()
    }
}
