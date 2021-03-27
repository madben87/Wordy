package com.ben.quizlet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ben.base.AutoClearedValue
import com.ben.quizlet.databinding.QuizletFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizletFragment : Fragment() {

    private val viewModel by viewModels<QuizletViewModel>()
    private var binding by AutoClearedValue<QuizletFragmentBinding>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = QuizletFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }
}