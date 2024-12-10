package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [(Student::class)], version = 1)
abstract class StudentRoomDatabase: RoomDatabase() {

    abstract fun StudentDao(): StudentDao

    // реализуем синглтон
    companion object {
        private var INSTANCE: StudentRoomDatabase? = null
        fun getInstance(context: Context): StudentRoomDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentRoomDatabase::class.java,
                        "StudentDb"

                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}