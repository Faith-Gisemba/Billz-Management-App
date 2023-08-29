package Repository.model

import Database.BillDb
import androidx.lifecycle.LiveData
import com.giseys.assessment.BillzApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Bill

class BillRepository {
    val db = BillDb.getDataBase(BillzApp.appContext)
    val billDao = db.billDao()

    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
           billDao.insertBill(bill)
        }

    }
    fun getAllBills(): LiveData<List<Bill>> {
        return billDao.getAllBills()
    }

}