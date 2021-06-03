package com.rojek.projekt.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rojek.projekt.NoteActivity
import com.rojek.projekt.databinding.NoteBinding
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.Note


object NoteDiff : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.description == newItem.description
    }

}
class NoteViewHolder(val noteBinding: NoteBinding) : RecyclerView.ViewHolder(noteBinding.root){

    fun bind(note: Note){
        noteBinding.idTextView.text =
                "${note.id}"
        noteBinding.noteDescriptionTextView.text =
            "${note.description}"


    }
}

class NoteListAdapter() : androidx.recyclerview.widget.ListAdapter<Note, NoteViewHolder>(NoteDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteBinding.inflate(inflater,parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}