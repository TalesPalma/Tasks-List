package com.example.listadetarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context) : SQLiteOpenHelper(
    context, "tasks", null, 1
) {

    companion object {
        const val TABLE_NAME = "tasks"
        const val ID = "id"
        const val TITLE = "titulo"
        const val DATE = "datetaks"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(\n" +
                    "\t$ID integer not null PRIMARY key AUTOINCREMENT,\n" +
                    "  \t$TITLE varchar(50),\n" +
                    "  \t$DATE datetime not NULL DEFAULT CURRENT_TIMESTAMP\n" +
                    ");"
            db?.execSQL(sql)
            Log.i("log_database", "SUCCESS CREATE TABLE")
        } catch (e: Exception) {
            Log.i("log_database", "ERROR CREATE TABLE -> ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("log_database", "Upgrade database")
    }

}