package Repository.model

import Database.BillDb
import androidx.lifecycle.LiveData
import com.giseys.assessment.BillzApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Bill
import model.upcomingBill
import utils.Constant
import utils.DateTimeUtils
import java.util.UUID

class BillRepository {
    val database=BillDb.getDataBase(BillzApp.appContext)
    val BillDao = database.billDao()
    val upcomingBillDao=database.upcomingBillsDao()

    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            BillDao.insertBill(bill)
        }
    }
    fun getAllBills(): LiveData<List<Bill>> {
        return BillDao.getAllBills()
    }

    suspend fun insertUpcomingBill(upcomingBill: upcomingBill){
        withContext(Dispatchers.IO){
            upcomingBillDao.insertUpcomingBill(upcomingBill)
        }
    }
    suspend fun createRecurringMonthlyBills(){
        withContext(Dispatchers.IO){
            val monthlyBills=BillDao.getRecurringBills(Constant.MONTHLY)
            val startDate=DateTimeUtils.getFirstDayOfMonth()
            val endDate= DateTimeUtils.getLastDayOfMonth()
            val year=DateTimeUtils.getCurrentYear()
            val month=DateTimeUtils.getCurrentMonth()
            monthlyBills.forEach { bill->
                val existing=upcomingBillDao.queryExisting(bill.billId,startDate, endDate)
                if (existing.isEmpty()){
                    val newUpcomingBill=upcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = "${bill.dueDate}/$month/$year",
                        UserId = bill.userId,
                        paid = false
                    )
                    upcomingBillDao.insertUpcomingBill(newUpcomingBill)

                }
            }
        }
    }
    suspend fun createRecurringWeeklyBills(){
        withContext(Dispatchers.IO){
            val weeklyBills=BillDao.getRecurringBills(Constant.WEEKLY)
            val startDate=DateTimeUtils.getFirstDayOfWeek()
            val endDate=DateTimeUtils.getLastDateOfWeek()
            weeklyBills.forEach { bill->
                val existingBill=upcomingBillDao.queryExisting(bill.billId,startDate, endDate)
                if (existingBill.isEmpty()){
                    val newWeeklyBills=upcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDateOfWeekDay(bill.dueDate),
                        UserId = bill.userId,
                        paid = false
                    )
                    upcomingBillDao.insertUpcomingBill(newWeeklyBills)
                }
            }


        }
    }




}