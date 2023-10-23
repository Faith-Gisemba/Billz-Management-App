package com.giseys.assessment.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName ="upcomingBills",
indices = [Index(value = ["billId", "dueDate"], unique = true)])
data class upcomingBill(
    @Expose @SerializedName("upcoming_bill_id") @PrimaryKey var upcomingBillId: String,
    @Expose @SerializedName("bill_id")var billId: String,
    @Expose var name: String,
    @Expose var amount: Double,
    @Expose var frequency: String,
    @Expose @SerializedName("due_date")var dueDate: String,
    @Expose @SerializedName("user_id") var UserId: String,
    @Expose var paid: Boolean,
    var synced: Boolean
)