package com.rojek.projekt.records

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.Inventory
import com.rojek.projekt.entities.Note
import com.rojek.projekt.entities.Record

class RecordViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase(application)
    val recordDAO = appDatabase.recordDao()

    fun getRecords(username: String,date:String): LiveData<List<Record>>{
        return recordDAO.readAllRecords(username,date)
    }
    fun deleteRecord(item: String){
        appDatabase.transactionExecutor.execute {
            recordDAO.deleteRecord(item)
        }
    }
    fun addNewRecord(record: Record){
        appDatabase.transactionExecutor.execute{
            recordDAO.addRecord(record)
        }

    }

}