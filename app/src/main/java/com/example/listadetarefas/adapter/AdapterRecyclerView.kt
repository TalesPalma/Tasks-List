package com.example.listadetarefas.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listadetarefas.Task
import com.example.listadetarefas.databinding.ItemRecyclerViewBinding
import com.example.listadetarefas.model.TaskDAO
import kotlin.coroutines.coroutineContext

class AdapterRecyclerView(
    val onclickDelete:(Int)-> Unit,
    val onclickUpdate:(Task)-> Unit
) : Adapter<AdapterRecyclerView.ViewHolderRecyclerView>() {

    private var listTasks: List<Task> = emptyList()


    @SuppressLint("NotifyDataSetChanged")
    fun addList(context:Context) {
        this.listTasks = TaskDAO(context).select()
        notifyDataSetChanged()
    }

    inner class ViewHolderRecyclerView(itembinding: ItemRecyclerViewBinding) :
        ViewHolder(itembinding.root) {
        val binding: ItemRecyclerViewBinding = itembinding





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecyclerView {
        val item = ItemRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolderRecyclerView(item)
    }

    override fun getItemCount(): Int {
        return listTasks.size
    }

    override fun onBindViewHolder(holder: ViewHolderRecyclerView, position: Int) {

        with(holder.binding) {
            textViewTask.text = listTasks[position].title
            textViewDate.text = listTasks[position].date
            btnRemover.setOnClickListener { onclickDelete(listTasks[position].id) }
            btnEditar.setOnClickListener { onclickUpdate(
                Task(listTasks[position].id,listTasks[position].title,listTasks[position].date)
            ) }
        }

    }
}