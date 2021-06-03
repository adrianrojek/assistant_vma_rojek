package com.rojek.projekt.records

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rojek.projekt.InventoryActivity
import com.rojek.projekt.RecordActivity
import com.rojek.projekt.databinding.InventoryBinding
import com.rojek.projekt.databinding.NoteBinding
import com.rojek.projekt.databinding.RecordBinding
import com.rojek.projekt.entities.Record


object RecordDiff : DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.item == newItem.item
    }

}
class RecordViewHolder(val recordBinding: RecordBinding) : RecyclerView.ViewHolder(recordBinding.root){

    fun bind(record: Record,context: Context){
        recordBinding.itemTextView.text =
            "${record.item}"
        recordBinding.amountTextView.text =
            "${record.count}"
        recordBinding.recordLayout.setOnClickListener{

        }

    }
}

class RecordListAdapter(private val context: Context) : androidx.recyclerview.widget.ListAdapter<Record, RecordViewHolder>(RecordDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecordBinding.inflate(inflater,parent,false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position),context)
    }

}