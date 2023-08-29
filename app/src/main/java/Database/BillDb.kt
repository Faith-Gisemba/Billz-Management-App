package Database
//dbul is used to create a new instance of the database using the provided context, class, and name.
//et up and manage a SQLite database using the Room Persistence Library
//Specifies an array of entity classes that define the tables in the database.
//define static members in Kotlin.
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Bill

@Database(entities = [Bill :: class], version = 1)
abstract class BillDb: RoomDatabase() {

    abstract fun billDao(): BillDao
    companion object{
        var database : BillDb? = null
        fun getDataBase(context: Context): BillDb{
            if (database == null) {
                database = Room
                    .databaseBuilder(context,BillDb::class.java,"BillDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillDb
        }
    }

    }