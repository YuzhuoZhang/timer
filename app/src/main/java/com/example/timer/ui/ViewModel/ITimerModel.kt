package com.example.timer.ui.ViewModel

import androidx.lifecycle.MutableLiveData

interface ITimerModel {
    abstract val label: CharSequence?
    val time: MutableLiveData<Long>
    fun startTimer()
    fun pauseTimer()
    fun resetTimer()
}