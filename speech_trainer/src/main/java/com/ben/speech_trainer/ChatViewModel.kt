package com.ben.speech_trainer

import androidx.lifecycle.ViewModel
import com.ben.core.Translator
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ChatViewModel constructor(
    private val translator: Translator
    ) : ViewModel()