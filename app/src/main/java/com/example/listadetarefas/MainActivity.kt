package com.example.listadetarefas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadetarefas.adapter.AdapterRecyclerView
import com.example.listadetarefas.databinding.ActivityMainBinding
import com.example.listadetarefas.model.TaskDAO

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var list = emptyList<Task>()
    var taskAdapter:AdapterRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        with(binding) {
            fabAdicionar.setOnClickListener {
                val intent = Intent(baseContext, AdicionarActivity::class.java)
                startActivity(intent)
            }
        }
        taskAdapter = AdapterRecyclerView(
            {id -> confirmarExlusao(id)},
            {taks -> editar(taks)}
            )
        binding.recyclerViewTasks.adapter = taskAdapter
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun editar(taks:Task) {

        val buider = AlertDialog.Builder(this)
        buider.setTitle("Confirmar update")
        buider.setMessage("Realmente deseja modificar essa tarefa ?")
        buider.setPositiveButton("SIM"){_,_ ->
            val intent = Intent(this,AdicionarActivity::class.java)
            intent.putExtra("id_item",taks.id)
            startActivity(intent)
        }
        buider.setNegativeButton("NÃO"){_,_ ->}
        buider.create().show()
    }

    private fun confirmarExlusao(id: Int) {
        val buider = AlertDialog.Builder(this)
        buider.setTitle("Confirma exclusão")
        buider.setMessage("Realmente deseja deletar essa tarefa ?")
        buider.setPositiveButton("SIM"){_,_ ->
            TaskDAO(this).delete(Task(id,"",""))
            taskAdapter!!.addList(this)
        }
        buider.setNegativeButton("NÃO"){_,_ ->}
        buider.create().show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        taskAdapter!!.addList(this)
    }
}