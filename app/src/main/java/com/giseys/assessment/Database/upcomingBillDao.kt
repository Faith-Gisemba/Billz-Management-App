package com.giseys.assessment.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.giseys.assessment.model.upcomingBill



@Dao
interface upcomingBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingBill(upcomingBill: upcomingBill)

    @Query("SELECT * FROM upcomingBills WHERE billId=:billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExisting(billId: String, startDate: String, endDate: String): List<upcomingBill>

    @Query("SELECT * FROM upcomingBills WHERE frequency = :freq AND paid = :paid")
    fun getUpcomingBillsByFrequency(freq: String, paid: Boolean): LiveData<List<upcomingBill>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUpcomingBill(upcomingBill: upcomingBill)

    @Query("SELECT * FROM UpcomingBills WHERE paid= :paid ORDER BY dueDate DESC")
    fun getPaidBills(paid: Boolean = true): LiveData<List<upcomingBill>>


    @Query("SELECT * FROM UpcomingBills WHERE synced = 0")
    fun getUnsynchedUpcomingBills():List<upcomingBill>


    @Query("SELECT SUM(amount) FROM upcomingBills WHERE dueDate BETWEEN:startDate AND :endDate")
    fun getTotalMonthlyBills(startDate: String, endDate: String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=1 AND  dueDate BETWEEN:startDate AND :endDate")
    fun getPaidMonthlyBillsSum(startDate: String, endDate: String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=0 AND dueDate BETWEEN:startDate AND :endDate AND dueDate > :today")
    fun getUpcomingBillsThisMonth(startDate: String,endDate: String,today:String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=0 AND dueDate BETWEEN:startDate AND :endDate AND dueDate < :today")
    fun getOverdueBillsThisMonth(startDate: String,endDate: String,today:String):Double


}
