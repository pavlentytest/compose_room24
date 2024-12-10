package com.example.myapplication

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentRepository(private val studentDao: StudentDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val studentList: LiveData<List<Student>> = studentDao.getStudents()

    fun addStudent(s: Student) {
        coroutineScope.launch(Dispatchers.IO) {
            studentDao.addStudent(s)
        }
    }

    fun deleteStudent(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            studentDao.deleteStudent(id)
        }
    }
}