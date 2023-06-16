package com.example.todolistapp_kotlin

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp_kotlin.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskNewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (taskItem != null) {
            binding.idTask.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.description.text = editable.newEditable(taskItem!!.description)
            if (taskItem!!.dueTime != null) {
                dueTime = taskItem!!.dueTime
                updateTimeButtonText()
            }
        } else {
            binding.idTask.text = "New Task"

        }
        taskViewModel = ViewModelProvider(activity).get(TaskNewModel::class.java)
        binding.saveBtn.setOnClickListener {
            saveAction()
        }
        binding.timePickerBtn.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if (dueTime==null){
            dueTime = LocalTime.now()
        }
        val listener = TimePickerDialog.OnTimeSetListener{_,selectedHour,selectedMinute ->
            dueTime = LocalTime.of(selectedHour,selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity,listener,dueTime!!.hour,dueTime!!.minute,true)
       dialog.setTitle("Task Due")
        dialog.show()

    }

    private fun updateTimeButtonText() {
        binding.timePickerBtn.text = String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.name.text.toString()
        val des = binding.description.text.toString()
        if (taskItem == null) {
            val newTask = TaskItem(name, des, dueTime, null)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.updateTaskItem(taskItem!!.id, name, des, dueTime)
        }

        binding.name.setText("")
        binding.description.setText("")
        dismiss()

    }


}