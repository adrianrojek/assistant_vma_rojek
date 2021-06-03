package com.rojek.projekt.inventories

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
import com.rojek.projekt.entities.Inventory


object InventoryDiff : DiffUtil.ItemCallback<Inventory>() {
    override fun areItemsTheSame(oldItem: Inventory, newItem: Inventory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Inventory, newItem: Inventory): Boolean {
        return oldItem.description == newItem.description
    }

}
class InventoryViewHolder(val inventoryBinding: InventoryBinding) : RecyclerView.ViewHolder(inventoryBinding.root){

    fun bind(inventory: Inventory,context: Context){
        inventoryBinding.dateTextView.text =
            "${inventory.date}"
        inventoryBinding.inventoryDescriptionTextView.text =
            "${inventory.description}"
        inventoryBinding.inventoryLayout.setOnClickListener{
            val invintent : Intent = Intent(context, RecordActivity::class.java)
            invintent.putExtra("username","${inventory.username}")
            invintent.putExtra("date","${inventory.date}")
            context.startActivity(invintent)
        }

    }
}

class InventoryListAdapter(private val context: Context) : androidx.recyclerview.widget.ListAdapter<Inventory, InventoryViewHolder>(InventoryDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InventoryBinding.inflate(inflater,parent,false)
        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        holder.bind(getItem(position),context)
    }

}