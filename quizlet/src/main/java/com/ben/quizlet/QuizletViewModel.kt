package com.ben.quizlet

import androidx.lifecycle.ViewModel
import com.ben.core.Translator
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class QuizletViewModel constructor(private val translator: Translator) : ViewModel() {}