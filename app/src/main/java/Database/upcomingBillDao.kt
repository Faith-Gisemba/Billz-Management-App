package Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.upcomingBill



@Dao
interface upcomingBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingBill(upcomingBill: upcomingBill)

    @Query("SELECT * FROM upcomingBills WHERE billId=:billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExisting(billId: String, startDate: String, endDate: String): List<upcomingBill>}
