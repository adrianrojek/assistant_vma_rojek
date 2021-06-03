package com.rojek.projekt.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase(application)
    val noteDAO = appDatabase.noteDao()

    fun getNotes(username: String): LiveData<List<Note>>{
        return noteDAO.readAllNotes(username)
    }

    fun deleteNote(id: Int){
        appDatabase.transactionExecutor.execute {
            noteDAO.deleteNote(id)
        }
    }

    fun addNewNote(note: Note){
        appDatabase.transactionExecutor.execute{
            noteDAO.addNote(note)
        }

    }



}