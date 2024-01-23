package com.example.listadetarefas.model

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.listadetarefas.Task
import com.example.listadetarefas.database.DataBaseHelper
import com.example.listadetarefas.databinding.ItemRecyclerViewBinding

class TaskDAO(context: Context) : ITaskDAO {
    private val writer = DataBaseHelper(context).writableDatabase
    private val read = DataBaseHelper(context).readableDatabase

    override fun insert(taks: Task): Boolean {
        try {
            val values = contentValuesOf(
                DataBaseHelper.TITLE to taks.title
            )
            writer.insert(DataBaseHelper.TABLE_NAME, null, values)
            Log.i("log_database", "SUCCESS INSERT DATABASE -")
        } catch (e: Exception) {
            Log.i("log_database", "ERROR INSERT DATABASE -> ${e.message}")
            return false
        }
        return true
    }

    override fun update(taks: Task): Boolean {
        try {
            val values = contentValuesOf(
                DataBaseHelper.TITLE to taks.title
            )
            val args = arrayOf(taks.id.toString())
            writer.update(
                DataBaseHelper.TABLE_NAME, values, "id = ?", args
            )
            Log.i("log_database", "SUCCESS UPDATE DATABASE -")
        } catch (e: Exception) {
            Log.i("log_database", "ERROR UPDATE DATABASE -> ${e.message}")
            return false
        }
        return true
    }

    override fun delete(taks: Task): Boolean {
        try {
            val args = arrayOf(taks.id.toString())
            writer.delete(
                DataBaseHelper.TABLE_NAME, "id = ?", args
            )
            Log.i("log_database", "SUCCESS DELETE DATABASE -")
        } catch (e: Exception) {
            Log.i("log_database", "ERROR DELETE DATE -> ${e.message}")
            return false
        }
        return true
    }

    override fun select(): List<Task> {
        val list = mutableListOf<Task>()
        try {
            val sql = "SELECT ${DataBaseHelper.ID} ," +
                    " ${DataBaseHelper.TITLE} ," +
                    " strftime('%d/%m/%Y %H:%M') " +
                    "FROM ${DataBaseHelper.TABLE_NAME}"
            val curso = read.rawQuery(sql, null)
            while (curso.moveToNext()) {
                list.add(
                    Task(
                        curso.getInt(0), curso.getString(1), curso.getString(2)
                    )
                )
            }
            Log.i("log_database", "SUCCESS SELECT DATES")
        } catch (e: Exception) {
            Log.i("log_database", "ERROR SELECT DATES -> ${e.message}")
        }
        return list
    }


}
