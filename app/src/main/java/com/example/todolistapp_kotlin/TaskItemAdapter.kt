package com.example.todolistapp_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp_kotlin.databinding.TaskItemCellBinding

class TaskItemAdapter (
    private val taskItems: List<TaskItem>,
    private val clickListener: TaskItemClickListener

): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(from,parent,false)
        return MyViewHolder(parent.context,binding,clickListener)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindingTaskItem(taskItems[position])
    }

    override fun getItemCount(): Int = taskItems.size

}