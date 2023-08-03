package com.example.project_1_home.`interface`

interface OnPassData {
    fun onPassData(title: String, body: String)
    fun onPassDataToUpdate(i: Int, title: String, body: String, checkBox: Boolean)
    fun onPassNumberTasks(numCompleted: Float, numActive: Float)
}