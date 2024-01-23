package com.example.listadetarefas.model

import com.example.listadetarefas.Task

interface ITaskDAO {
    fun insert(taks:Task):Boolean
    fun update(taks:Task):Boolean
    fun delete(taks:Task):Boolean
    fun select():List<Task>
}