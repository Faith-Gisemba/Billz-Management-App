package com.giseys.assessment.repository

import com.giseys.assessment.Api.ApiClient
import com.giseys.assessment.Api.ApiInterface
import com.giseys.assessment.Database.BillDb
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giseys.assessment.BillzApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.giseys.assessment.model.Bill
import com.giseys.assessment.model.BillsSummary
import com.giseys.assessment.model.upcomingBill
import com.giseys.assessment.utils.Constant
import com.giseys.assessment.utils.DateTimeUtils
import java.util.UUID

class BillRepository {
    val database= BillDb.getDataBase(BillzApp.appContext)
    val BillDao = database.billDao()
    val upcomingBillDao=database.upcomingBillsDao()
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            BillDao.insertBill(bill)
        }
    }
//    fun getAllBills(): LiveData<List<Bill>> {
//        return BillDao.getAllBills()
//    }

    suspend fun insertUpcomingBill(upcomingBill: upcomingBill){
        withContext(Dispatchers.IO){
            upcomingBillDao.insertUpcomingBill(upcomingBill)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createRecurringMonthlyBills(){
        withContext(Dispatchers.IO){
            val monthlyBills=BillDao.getRecurringBills(Constant.MONTHLY)
            val startDate= DateTimeUtils.getFirstDayOfMonth()
            val endDate= DateTimeUtils.getLastDayOfMonth()
//            val year=DateTimeUtils.getCurrentYear()
//            val month=DateTimeUtils.getCurrentMonth()
            monthlyBills.forEach { bill->
                val existing=upcomingBillDao.queryExisting(bill.billId,startDate, endDate)
                if (existing.isEmpty()){
                    val newUpcomingBill= upcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.createDateFromDay(bill.dueDate),
                        UserId = bill.userId,
                        paid = false,
                        synced = false

                    )
                    upcomingBillDao.insertUpcomingBill(newUpcomingBill)

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createRecurringWeeklyBills(){
        withContext(Dispatchers.IO){
            val weeklyBills=BillDao.getRecurringBills(Constant.WEEKLY)
            val startDate= DateTimeUtils.getFirstDayOfWeek()
            val endDate= DateTimeUtils.getLastDateOfWeek()
            weeklyBills.forEach { bill->
                val existingBill=upcomingBillDao.queryExisting(bill.billId,startDate, endDate)
                if (existingBill.isEmpty()){
                    val newWeeklyBills= upcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDateOfWeekDay(bill.dueDate),
                        UserId = bill.userId,
                        paid = false,
                        synced = false
                    )
                    upcomingBillDao.insertUpcomingBill(newWeeklyBills)
                }
            }


        }
    }


//    @RequiresApi(Build.VERSION_CODES.O)
//    suspend fun createRecurringQuarterlyBills() {
//        withContext(Dispatchers.IO) {
//            val quarterlyBills = BillDao.getRecurringBills(Constant.QUARTERLY)
//            val currentYear = DateTimeUtils.getCurrentYear()
//
//            for (quarter in 1..4) {
//                val startDate = DateTimeUtils.getQuarterStartDate(currentYear, quarter)
//                val endDate = DateTimeUtils.getQuarterEndDate(currentYear, quarter)
//
//                quarterlyBills.forEach { bill ->
//                    val dueDateAsInt = bill.dueDate.toInt()
//
//                    if (dueDateAsInt in 1..31 && quarter * 3 in 1..12) {
//                        val existingBill = upcomingBillDao.queryExisting(bill.billId, startDate, endDate)
//
//                        if (existingBill.isEmpty()) {
//                            val newQuarterlyBill = upcomingBill(
//                                upcomingBillId = UUID.randomUUID().toString(),
//                                billId = bill.billId,
//                                name = bill.name,
//                                amount = bill.amount,
//                                frequency = bill.frequency,
//                                dueDate = DateTimeUtils.createDateFromDayAndMonth(dueDateAsInt, quarter * 3),
//                                userId = bill.userId,
//                                paid = false,
//                                synced = false
//                            )
//                            upcomingBillDao.insertUpcomingBill(newQuarterlyBill)
//                        }
//                    } else {
//                        Log.e("BillsRepository", "Invalid day-of-month or month value for bill: ${bill.billId}")
//                    }
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createRecurringAnnualBills(){
        withContext(Dispatchers.IO){
            val annualBills = BillDao.getRecurringBills(Constant.YEARLY)
            val currentYear = DateTimeUtils.getCurrentYear()
            val startDate = "$currentYear-01-01"
            val endDate = "$currentYear-12-31"
            annualBills.forEach { bill ->
                val existing = upcomingBillDao.queryExisting(bill.billId, startDate,endDate)
                if (existing.isEmpty()){
                    val newWeeklyBills= upcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = "$currentYear-${bill.dueDate}",
                        UserId = bill.userId,
                        paid = false,
                        synced = false
                    )
                    upcomingBillDao.insertUpcomingBill(newWeeklyBills)

                }
            }
        }
    }



    fun getAuthToken(): String{
        val prefs = BillzApp.appContext
            .getSharedPreferences(Constant.PREFS, Context.MODE_PRIVATE)
        var token = prefs.getString(Constant.ACCESS_TOKEN, Constant.EMPTY_STRING)
        token ="Bearer $token"
        return token
    }

    fun getUpcomingBillsByFrequency(freq: String):LiveData<List<upcomingBill>>{
        return upcomingBillDao.getUpcomingBillsByFrequency(freq , false)
    }

    suspend fun updateUpcomingBill(upcomingBill: upcomingBill){
        withContext(Dispatchers.IO){
            upcomingBillDao.updateUpcomingBill(upcomingBill)
        }
    }
    fun getPaidBills(): LiveData<List<upcomingBill>>{
        return upcomingBillDao.getPaidBills()
    }

    suspend fun synchedBills(){
        withContext(Dispatchers.IO){

            var token = getAuthToken()
            val unsynchedBills = BillDao.getUnsyncedBill()
            unsynchedBills.forEach { bill ->
                val response= apiClient.postBill(token, bill)

                if (response.isSuccessful){
                    bill.synced = true
                    BillDao.saveBill(bill)
                }
            }
        }
    }
    suspend fun synchedUpcomingBills(){
        withContext(Dispatchers.IO){
            var token = getAuthToken()
            upcomingBillDao.getUnsynchedUpcomingBills().forEach { upcomingBill ->
                val response = apiClient.postUpcomingBills(token,upcomingBill)

                if (response.isSuccessful){
                    upcomingBill.synced = true
                    upcomingBillDao.updateUpcomingBill(upcomingBill)
                }
            }
        }
    }

    suspend fun fetchRemoteBills(){
        withContext(Dispatchers.IO){
            val response = apiClient.fetchRemoteBills(getAuthToken())
            if (response.isSuccessful){
                response.body()?.forEach { bill ->
                    bill.synced=true
                    BillDao.saveBill(bill) }
            }
        }
    }

    suspend fun fetchRemoteUpcomingBills(){
        withContext(Dispatchers.IO){
            val response = apiClient.fetchRemoteUpcomingBills(getAuthToken())
            if (response.isSuccessful){
                response.body()?.forEach { upcomingBill ->
                    upcomingBill.synced=true
                    upcomingBillDao.insertUpcomingBill(upcomingBill) }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMonthlySummary():LiveData<BillsSummary>{
        return withContext(Dispatchers.IO){
            val startDate = DateTimeUtils.getFirstDayOfMonth()
            val endDate = DateTimeUtils.getLastDayOfMonth()
            val today = DateTimeUtils.getDateToday()
            val paid= upcomingBillDao.getPaidMonthlyBillsSum(startDate, endDate)
            val upcoming= upcomingBillDao.getUpcomingBillsThisMonth(startDate, endDate, today)
            val total = upcomingBillDao.getTotalMonthlyBills(startDate, endDate)
            val overdue = upcomingBillDao.getOverdueBillsThisMonth(startDate,endDate,today)
            val summary= BillsSummary(paid=paid, overdue= overdue, upcoming = upcoming, total = total)
            MutableLiveData(summary)
        }
    }
}