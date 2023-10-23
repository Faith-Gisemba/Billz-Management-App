package com.giseys.assessment

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.giseys.assessment.utils.Constant
import com.giseys.assessment.workmanager.DataSyncWorker
import com.giseys.assessment.workmanager.UpcomingBillsWorker
import java.util.concurrent.TimeUnit

class BillzApp : Application(){
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        val periodicWorkRequest = PeriodicWorkRequestBuilder<UpcomingBillsWorker>(15, TimeUnit.MINUTES)
            .addTag(Constant.CREATE_RECURRING_BILLS).build()
        WorkManager
            .getInstance(appContext)
            .enqueueUniquePeriodicWork(Constant.CREATE_RECURRING_BILLS, ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest)


        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val syncPeriodicWorkRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(15,TimeUnit.MINUTES)
            .addTag(Constant.SYNC_BILLS).setConstraints(constraints).build()

        WorkManager.getInstance(appContext)
            .enqueueUniquePeriodicWork(Constant.SYNC_BILLS,ExistingPeriodicWorkPolicy.KEEP,syncPeriodicWorkRequest)



    }
}