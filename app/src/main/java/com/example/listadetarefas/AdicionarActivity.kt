package com.example.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listadetarefas.databinding.ActivityAdicionarBinding
import com.example.listadetarefas.model.TaskDAO

class AdicionarActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityAdicionarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnAdcionar.setOnClickListener {
                val putExtras = intent.getIntExtra("id_item",-1)
                if(putExtras != -1){
                    update(putExtras)
                }else {
                    save()
                }
            }
        }

    }

    private fun update(id:Int){
        TaskDAO(baseContext).update(Task(id,binding.edtAdicionar.text.toString(),""))
        Toast.makeText(this,"Taks modificada",Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun save() {


        if(binding.edtAdicionar.text.isNotEmpty()) {
            val task = TaskDAO(this)
            val values = Task(-1, binding.edtAdicionar.text.toString(), "default")

            if(task.insert(values)){
                Toast.makeText(
                    this,
                    "Tarefa adicionada",
                    Toast.LENGTH_SHORT)
                    .show()
                finish()
            }else{
                Toast.makeText(
                    this,
                    "Não foi possivel add a tarefa",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }else{
            Toast.makeText(
                this,
                "Preencha o campo para adicionar um tarefa",
                Toast.LENGTH_SHORT)
                .show()
        }


    }
}