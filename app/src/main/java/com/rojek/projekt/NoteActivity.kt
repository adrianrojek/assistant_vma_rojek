package com.rojek.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rojek.projekt.entities.Note
import com.rojek.projekt.notes.NoteListAdapter
import com.rojek.projekt.notes.NoteViewModel

class NoteActivity : AppCompatActivity() {

        val noteViewModel: NoteViewModel by viewModels()
        override fun onCreate(savedInstanceState: Bundle?) {
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)


        val noteRecyclerView: RecyclerView = findViewById(R.id.inventoryRecyclerView)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)

        NoteListAdapter().also {
            noteRecyclerView.adapter = it

            if (loggedUsername != null) {
                noteViewModel.getNotes(loggedUsername).observe(this, it::submitList)
            }

        }


    }
    fun deleteNote(view: View){
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Vymazať poznámku")
            .setMessage("Zadajte ID poznámky, ktorú chcete vymazať!")
            .setView(editText)
            .setPositiveButton("Vymazať"){_,_ ->
                noteViewModel.deleteNote(editText.text.toString().toInt())
            }
            .create()
            .show()
    }
    fun addNewNote(view: View){
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Nová poznámka")
            .setMessage("Zadajte text poznámky!")
            .setView(editText)
            .setPositiveButton("Pridať"){_,_ ->
                noteViewModel.addNewNote(Note(0,loggedUsername.toString(),editText.text.toString()))
            }
            .create()
            .show()

        //noteViewModel.addNewNote(Note(0,"test","test"))
    }


}