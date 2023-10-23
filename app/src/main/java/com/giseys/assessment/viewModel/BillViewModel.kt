package com.giseys.assessment.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import com.giseys.assessment.repository.BillRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.giseys.assessment.model.Bill
import com.giseys.assessment.model.BillsSummary
import com.giseys.assessment.model.upcomingBill

class BillViewModel: ViewModel() {
    var billRepo= BillRepository()
    val summaryLiveData = MediatorLiveData<BillsSummary>()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billRepo.saveBill(bill)
        }

    }
//    fun getAllBills(): LiveData<List<Bill>> {
//        return billRepo.getAllBills()
//    }

    fun getUpcomingBillsByFre(freq:String): LiveData<List<upcomingBill>>{
        return billRepo.getUpcomingBillsByFrequency(freq)
    }
    fun updateUpcomingBill(upcomingBill: upcomingBill){
        viewModelScope.launch {
            billRepo.updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBills(): LiveData<List<upcomingBill>>{
        return billRepo.getPaidBills()
    }

    fun fetchRemoteBills(){
        viewModelScope.launch {
            billRepo.fetchRemoteBills()
            billRepo.fetchRemoteUpcomingBills()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlySummary(){
        viewModelScope.launch {
            summaryLiveData.postValue(billRepo.getMonthlySummary().value)
        }
    }

}

