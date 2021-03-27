package com.ben.core

import com.ben.core.model.Language
import com.ben.core.model.Result

interface ITranslator {
    fun getLanguages(callback: (Result<Any>) -> Unit)//List<Language>
    fun translate(text: String, callback: (Result<Any>) -> Unit)
}