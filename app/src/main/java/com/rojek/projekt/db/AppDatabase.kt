package com.rojek.projekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rojek.projekt.dao.InventoryDAO
import com.rojek.projekt.dao.NoteDAO
import com.rojek.projekt.dao.RecordDAO
import com.rojek.projekt.dao.UserDAO
import com.rojek.projekt.entities.Inventory
import com.rojek.projekt.entities.Note
import com.rojek.projekt.entities.Record
import com.rojek.projekt.entities.User



@Database(entities = [User::class, Note::class, Record::class, Inventory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun noteDao(): NoteDAO
    abstract fun recordDao(): RecordDAO
    abstract fun inventoryDao(): InventoryDAO

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        operator fun invoke(context: Context): AppDatabase =
            db ?: synchronized(this){
                db ?: context.buildDatabase()
                    .also{
                        db = it
                    }
            }

        private fun Context.buildDatabase(): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app_database"
            )   .allowMainThreadQueries()
                .build()


        }
    }
}