package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bills")
data class Bill(

    @PrimaryKey var billId: String,
    var name: String,
    var amount:Double,
    var frequency:String,
    var dueDate:String,
    var userId: String

)
