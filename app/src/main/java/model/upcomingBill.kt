package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="upcomingBills")
data class upcomingBill(
    @PrimaryKey var upcomingBillId: String,
    var billId: String,
    var name: String,
    var amount: Double,
    var frequency: String,
    var dueDate: String,
    var UserId: String,
    var paid: Boolean
)