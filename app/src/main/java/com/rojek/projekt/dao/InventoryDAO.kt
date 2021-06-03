package com.rojek.projekt.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rojek.projekt.entities.Inventory

@Dao
interface InventoryDAO {

    @Insert
    fun addInventory(inventory: Inventory)

    @Query("Select * from inventories where username=:username")
    fun readAllInventories(username: String): LiveData<List<Inventory>>

    @Query("Delete from inventories where date=:date")
    fun deleteInventory(date: String)


}
