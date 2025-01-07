package com.victorsdd.androidmaster.my_app.todoApp.models

sealed class TaskCategory(var isSelected: Boolean = true) {
    data object Personal : TaskCategory()
    data object Business : TaskCategory()
    data object Other : TaskCategory()

}