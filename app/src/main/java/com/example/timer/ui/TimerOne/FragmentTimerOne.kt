package com.example.timer.ui.TimerOne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.timer.ui.ViewModel.TimerViewModel
import com.example.timer.databinding.FragmentTimerOneBinding

class FragmentTimerOne : Fragment() {
    private var _binding: FragmentTimerOneBinding? = null
    private val binding get() = _binding!!
    private val timerViewModel: TimerOneViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimerOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timerLabel.text=timerViewModel.label;
        timerViewModel.time.observe(viewLifecycleOwner, Observer { elapsedTime ->
            // Update UI with elapsedTime formatted as HH:mm:ss
            val hours = elapsedTime / 3600
            val minutes = (elapsedTime % 3600) / 60
            val seconds = elapsedTime % 60
            binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        })

        binding.startButton.setOnClickListener { timerViewModel.startTimer() }
        binding.stopPauseButton.setOnClickListener { timerViewModel.pauseTimer() }
        binding.resetButton.setOnClickListener { timerViewModel.resetTimer() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
