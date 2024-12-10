package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class StudentViewModel(application: Application) : ViewModel() {

    val studentList: LiveData<List<Student>>
    private val repository: StudentRepository
    var studentName by mutableStateOf("")
    var studentAge by mutableStateOf(0)

    init {
        val studentDb = StudentRoomDatabase.getInstance(application)
        val studentDao = studentDb.StudentDao()
        repository = StudentRepository(studentDao)
        studentList = repository.studentList!!
    }
    fun changeName(value: String){
        studentName = value
    }
    fun changeAge(value: String){
        studentAge = value.toIntOrNull()?:studentAge
    }
    fun addUser() {
        repository.addStudent(Student(studentName, studentAge))
    }
    fun deleteUser(id: Int) {
        repository.deleteStudent(id)
    }
}