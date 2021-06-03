package com.rojek.projekt

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rojek.projekt.entities.Record
import com.rojek.projekt.records.RecordListAdapter
import com.rojek.projekt.records.RecordViewModel
import kotlinx.android.synthetic.main.inventory_dialog.view.*


class RecordActivity : AppCompatActivity() {


    val recordViewModel: RecordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        var date =bundle!!.getString("date")
        println(loggedUsername.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val recordRecyclerView: RecyclerView = findViewById(R.id.recordRecyclerView)
        recordRecyclerView.layoutManager = LinearLayoutManager(this)

        RecordListAdapter(this).also {
            recordRecyclerView.adapter = it
            if (loggedUsername != null) {
                recordViewModel.getRecords(loggedUsername,date.toString()).observe(this, it::submitList)
            }

        }

    }
    fun deleteRecord(view: View){
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Vymazať poznámku")
            .setMessage("Zadajte dátum inventúry, ktorú chcete vymazať!")
            .setView(editText)
            .setPositiveButton("Vymazať"){_,_ ->
                recordViewModel.deleteRecord(editText.text.toString())
            }
            .create()
            .show()
    }

    fun addNewRecord(view: View){
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        var date = bundle!!.getString("date")
        val inventoryDialog = LayoutInflater.from(this).inflate(R.layout.record_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this,R.style.customDialog)
            .setView(inventoryDialog)
            .setTitle("Pidať nový záznam o inventúre")
        val inventoryAlertDialog = dialogBuilder.show()
        inventoryDialog.addRecordDialogButton.setOnClickListener{
            inventoryAlertDialog.dismiss()
            val recordItem = inventoryDialog.recordItem.text.toString()
            val recordAmount = inventoryDialog.recordAmount.text.toString()
            recordViewModel.addNewRecord(Record(0,loggedUsername.toString(),recordItem,recordAmount,date.toString()))
        }


    }


}