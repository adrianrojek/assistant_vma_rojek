package com.rojek.projekt.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rojek.projekt.entities.Note
import com.rojek.projekt.entities.User

@Dao
interface NoteDAO {

    @Insert
    fun addNote(note: Note)

    @Query("Select * from notes where username=:username")
    fun readAllNotes(username:String): LiveData<List<Note>>

    @Query("Delete from notes where id=:id")
    fun deleteNote(id: Int)




}