package com.mp.birthdays.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, dbName, null, 1) {

    val colID = "ID"
    val colName = "Name"
    val colDate = "Date"
    val colColor = "Color"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY,"+
            "$colName TEXT, $colDate TEXT, $colColor TEXT);"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(sqlCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $dbName")
        onCreate(db)
    }


    fun insert(name: String, birthday: String, color: Int): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, name)
        contentValues.put(COL_2, birthday)
        contentValues.put(COL_3, color)

        return db.insert(dbTable, null, contentValues)
    }

    fun delete(name: String){
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT $COL_0 FROM $dbTable WHERE $COL_1='$name'", null)
        var id = 0
        while(res.moveToNext()){
            id = res.getString(0).toInt()
        }
        db.delete(dbTable, "ID = $id",null)
    }

    fun getBirthdays(): Cursor{
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM $dbTable", null)
        return res
    }

    companion object {
        val dbName = "Birthdays"
        val dbTable = "BirthdaysTable"
        val COL_0 = "ID"
        val COL_1 = "Name"
        val COL_2 = "Date"
        val COL_3 = "Color"
    }

}