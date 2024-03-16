package com.example.timer.ui.TimerThree

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timer.ui.ViewModel.ITimerModel
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask

class TimerThreeViewModel : ViewModel(), ITimerModel {
    override var label: CharSequence? = "Timer Three"
    override val time = MutableLiveData<Long>()
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null // Hold the timer task
    private var elapsedTime = 0L
    private var isTimerRunning = false // Flag to track timer state

    override fun startTimer() {
        if (!isTimerRunning) { // Initialize timer if it's null
            timer = Timer()
            isTimerRunning=true
            timerTask = timerTask {
                elapsedTime++
                time.postValue(elapsedTime)
            }
            timer?.scheduleAtFixedRate(timerTask, 1000, 1000)
        }
    }

    override fun pauseTimer() {
        timerTask?.cancel() // Cancel the current task
        timer?.purge() // Remove all cancelled tasks from this timer's task queue.
        isTimerRunning=false
    }

    override fun resetTimer() {
        pauseTimer()
        isTimerRunning=false
        elapsedTime = 0L
        time.postValue(elapsedTime)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel() // Ensure timer is cancelled when ViewModel is cleared
        timer = null
    }
}
