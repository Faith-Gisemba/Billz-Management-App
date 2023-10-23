package com.giseys.assessment.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giseys.assessment.model.Bill
import com.giseys.assessment.model.upcomingBill

//dbul is used to create a new instance of the database using the provided context, class, and name.
//et up and manage a SQLite database using the Room Persistence Library
//Specifies an array of entity classes that define the tables in the database.
//define static members in Kotlin.

@Database(entities = [Bill::class, upcomingBill::class], version = 5)
abstract class BillDb : RoomDatabase() {

    abstract fun billDao(): BillDao
    abstract fun upcomingBillsDao(): upcomingBillDao

    companion object {
        var database: BillDb? = null
        fun getDataBase(context: Context): BillDb {
            if (database == null) {
                database = Room
                    .databaseBuilder(context, BillDb::class.java, "BillDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillDb
        }
    }

}