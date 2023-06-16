package com.example.todolistapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),TaskItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskNewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskNewModel::class.java)
        binding.newTaskBtn.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager,"newTaskTag")
        }
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.recyclerview.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }


    }

    override fun editTaskItem(taskItem: TaskItem) {
NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }

}