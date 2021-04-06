package com.mp.birthdays.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mp.birthdays.R
import com.mp.birthdays.database.DatabaseHelper
import com.mp.birthdays.model.birthday
import kotlinx.android.synthetic.main.birthday.view.*
import kotlin.collections.ArrayList

class BirthdayAdapter(private val cardlist: ArrayList<birthday>, val context: Context):
    RecyclerView.Adapter<BirthdayAdapter.BirthdayViewholder>(){

    var colorValue = 0

    class BirthdayViewholder(cardView: View): RecyclerView.ViewHolder(cardView) {
        val name: TextView = cardView.name
        val birthday: TextView = cardView.birthday

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.birthday,
            parent, false
        )

        itemView.delete.setOnClickListener{
            val db = DatabaseHelper(context)
            db.delete(itemView.name.text.toString())
            val cursor = db.getBirthdays()

            cardlist.clear()

            while(cursor.moveToNext()){
                cardlist.add(birthday(cursor.getString(1), cursor.getString(2), cursor.getInt(3)))
            }
            notifyDataSetChanged()
        }
        return BirthdayViewholder(itemView)
    }

    override fun onBindViewHolder(holder: BirthdayViewholder, position: Int) {
        val currentItem = cardlist[position]
        holder.name.text = currentItem.name
        holder.birthday.text = currentItem.birthday
        colorValue = currentItem.color
    }

    override fun getItemCount(): Int {
        return cardlist.size
    }

}