package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student(@ColumnInfo(name = "studentName") var name: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "studentId")
    var id: Int = 0
}