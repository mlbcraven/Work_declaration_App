package com.example.spinnerrecycler.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.CheckBox
import androidx.core.database.getBlobOrNull
import java.lang.Exception

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        val DATABASE_NAME:String = "entries.db"
        val DATABASE_VERSION: Int = 5

        val TABLE_NAME: String = "entries"
        val KEY_ID: String = "id"
        var KEY_PROBLEMS: String = "problems"
        val KEY_NAME : String = "name"
        val KEY_WORK : String = "work"
        val KEY_TIMESTAMP : String = "timestamp"
        val KEY_CHBSTART : String = "chbStart"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                KEY_NAME + " TEXT," +
                KEY_WORK + " TEXT," +
                KEY_TIMESTAMP + " TEXT," +
                KEY_CHBSTART + " TEXT," +
                KEY_PROBLEMS + " TEXT "

                 + ")")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, odlVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addEntry(entry: Entry) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(KEY_ID,entry.id)
        contentValues.put(KEY_NAME,entry.name)
        contentValues.put(KEY_WORK,entry.work)
        contentValues.put(KEY_PROBLEMS,entry.problems)
        contentValues.put(KEY_TIMESTAMP,entry.timestamp)
        contentValues.put(KEY_CHBSTART,entry.chbStart)

        val success = db.insert(TABLE_NAME , null, contentValues)
        db.close()
        return success

    }
    fun getEntry(): ArrayList<Entry> {
        val entryList: ArrayList<Entry> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e:Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var work:String
        var timestamp : String
        var problems :String
        var chbStart: Boolean


        if (cursor.moveToFirst()) {
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                work = cursor.getString(cursor.getColumnIndex("work"))
                timestamp = cursor.getString(cursor.getColumnIndex("timestamp"))
                problems = cursor.getString(cursor.getColumnIndex("problems"))
                chbStart = cursor.getString(cursor.getColumnIndex("chbStart")).toBoolean()

                val ent = Entry(id = id, name = name , work = work, timestamp = timestamp, problems = problems,chbStart = chbStart)
                entryList.add(ent)

            }while (cursor.moveToNext())
        }
        return entryList
    }
    fun updateEntry(entry: Entry) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID,entry.id)
        contentValues.put(KEY_NAME,entry.name)
        contentValues.put(KEY_WORK,entry.work)
        contentValues.put(KEY_PROBLEMS,entry.problems)
        contentValues.put(KEY_TIMESTAMP,entry.timestamp)
        contentValues.put(KEY_CHBSTART,entry.chbStart)

        val success = db.update(TABLE_NAME, contentValues,"id=" + entry.id,null)
        db.close()
        return success
    }
    fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }

    fun deleteEntryById(id: Int) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID,id)
        val success = db.delete(TABLE_NAME,"id=$id",null)
        db.close()
        return success

    }
}