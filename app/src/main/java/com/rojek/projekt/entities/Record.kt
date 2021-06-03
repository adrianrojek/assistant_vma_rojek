package com.rojek.projekt.entities

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "item")
    val item: String,
    @ColumnInfo(name = "count")
    val count: String,
    @ColumnInfo(name = "date")
    val date: String
)