package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getStudents(): LiveData<List<Student>>

    @Insert
    fun addStudent(s: Student)

    @Query("DELETE FROM student WHERE studentId = :id")
    fun deleteStudent(id:Int)
}