package com.rojek.projekt.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rojek.projekt.entities.Record
import java.util.*

@Dao
interface RecordDAO {

    @Insert
    fun addRecord(record: Record)

    @Query("Select * from records where username=:username and date=:date")
    fun readAllRecords(username: String, date: String): LiveData<List<Record>>

    @Query("Delete from records where item=:item")
    fun deleteRecord(item: String)





}