package com.giseys.assessment.Database
////specifies the conflict resolution strategy in case the inserted data conflicts with existing data.
////annotation room library


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giseys.assessment.model.Bill
import com.giseys.assessment.model.upcomingBill


@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBill(bill: Bill)

    @Query("SELECT * FROM Bills WHERE frequency = :freq")
    fun getRecurringBills(freq: String): List<Bill>

    @Query("SELECT * FROM Bills WHERE synced=0")
    fun getUnsyncedBill(): List<Bill>

//    @Query("SELECT * FROM Bills")
//    fun getAllBills(): LiveData<List<Bill>>



}
