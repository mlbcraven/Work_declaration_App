package com.example.spinnerrecycler.db

import android.widget.CheckBox
import android.widget.Switch
import java.sql.Time
import java.sql.Timestamp
import java.util.*
import kotlin.random.Random

data class Entry (

    var id: Int = 0,
    var name : String ="",
    var work: String = "" ,
    var problems: String = "" ,
    var timestamp: String = "",
    var chbStart : Boolean


 )
{
    companion object {
        fun autoGenerate(): Int {
            var random : java.util.Random = java.util.Random()
            return random.nextInt(100)
        }
    }
}

