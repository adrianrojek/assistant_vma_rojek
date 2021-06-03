package com.rojek.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rojek.projekt.entities.Inventory
import com.rojek.projekt.inventories.InventoryListAdapter
import com.rojek.projekt.inventories.InventoryViewModel
import kotlinx.android.synthetic.main.inventory_dialog.view.*


class InventoryActivity : AppCompatActivity() {


    val inventoryViewModel: InventoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val inventoryRecyclerView: RecyclerView = findViewById(R.id.inventoryRecyclerView)
        inventoryRecyclerView.layoutManager = GridLayoutManager(this,2)

        InventoryListAdapter(this).also {
            inventoryRecyclerView.adapter = it
            if (loggedUsername != null) {
                inventoryViewModel.getInventories(loggedUsername).observe(this, it::submitList)
            }

        }

    }
    fun deleteInventory(view: View){
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Vymazať poznámku")
            .setMessage("Zadajte dátum inventúry, ktorú chcete vymazať!")
            .setView(editText)
            .setPositiveButton("Vymazať"){_,_ ->
                inventoryViewModel.deleteInventory(editText.text.toString().toString())
            }
            .create()
            .show()
    }
    fun addNewInventory(view: View){
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        val inventoryDialog = LayoutInflater.from(this).inflate(R.layout.inventory_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this,R.style.customDialog)
            .setView(inventoryDialog)
            .setTitle("Pidať nový záznam o inventúre")
        val inventoryAlertDialog = dialogBuilder.show()
        inventoryDialog.addRecordDialogButton.setOnClickListener{
            inventoryAlertDialog.dismiss()
            val inventoryDate = inventoryDialog.recordItem.text.toString()
            val inventoryDescription = inventoryDialog.recordAmount.text.toString()
            inventoryViewModel.addNewInventory(Inventory(0,loggedUsername.toString(),inventoryDate,inventoryDescription))
        }


    }


}