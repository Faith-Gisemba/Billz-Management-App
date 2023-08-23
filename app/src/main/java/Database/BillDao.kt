package Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import model.Bill

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill)
}