package com.example.todolistapp_kotlin

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem(
    var name :String,
    var description: String,
    var dueTime: LocalTime?,
    var completedDate: LocalDate?,
    var id :UUID=UUID.randomUUID()
) {
    fun isCompleted() = completedDate!=null

    fun imageResource():Int= if (isCompleted()) R.drawable.check else R.drawable.uncheck
    fun imageColor(context: Context):Int= if(isCompleted())purple(context) else black(context)


    fun purple(context: Context) = ContextCompat.getColor(context,R.color.purple_500)
    fun black(context: Context) = ContextCompat.getColor(context,R.color.black)


}