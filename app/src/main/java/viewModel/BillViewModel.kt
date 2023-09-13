package viewModel

import Repository.model.BillRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Bill

class BillViewModel: ViewModel() {
    var billRepo= BillRepository()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billRepo.saveBill(bill)
        }

    }
    fun getAllBills(): LiveData<List<Bill>> {
        return billRepo.getAllBills()
    }

    fun createRecurringBills(){
        viewModelScope.launch {
            billRepo.createRecurringMonthlyBills()
            billRepo.createRecurringWeeklyBills()
        }
    }

}