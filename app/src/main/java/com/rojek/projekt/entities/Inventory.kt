package com.rojek.projekt.entities

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "inventories",
    foreignKeys = arrayOf(
        ForeignKey(entity = User::class,
        parentColumns = arrayOf("username"),
    childColumns = arrayOf("username"),
    onDelete = CASCADE)
    ))
data class Inventory (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "description")
    val description: String


)