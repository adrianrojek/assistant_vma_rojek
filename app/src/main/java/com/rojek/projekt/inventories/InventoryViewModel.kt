package com.rojek.projekt.inventories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.Inventory
import com.rojek.projekt.entities.Note

class InventoryViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase(application)
    val inventoryDAO = appDatabase.inventoryDao()

    fun getInventories(username: String): LiveData<List<Inventory>>{
        return inventoryDAO.readAllInventories(username)
    }

    fun deleteInventory(date: String){
        appDatabase.transactionExecutor.execute {
            inventoryDAO.deleteInventory(date)
        }
    }

    fun addNewInventory(inventory: Inventory){
        appDatabase.transactionExecutor.execute{
            inventoryDAO.addInventory(inventory)
        }

    }

}