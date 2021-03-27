package com.ben.core

import com.ben.core.model.Language
import com.ben.core.model.Result
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import javax.inject.Inject

class Translator @Inject constructor() : ITranslator {
    private var isDownloaded: Boolean = false

    private val modelManager = RemoteModelManager.getInstance()

    private val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.RUSSIAN)
        .build()

    private val englishRussianTranslator = Translation.getClient(options)

    var conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()

    init {
        checkIfDownload()
    }

    override fun getLanguages(callback: (Result<Any>) -> Unit) {
        val availableList = TranslateLanguage.getAllLanguages()

        modelManager
            .getDownloadedModels(TranslateRemoteModel::class.java)
            .addOnCompleteListener {
                if (it.isComplete) {
                    callback.invoke(
                        Result.Success.Value(availableList.map(it.result?.map { lang -> lang.language })))
                }
            }
            .addOnFailureListener {
                callback.invoke(
                    Result.Failure.Error(it)
                )
            }
    }

    private fun List<String>.map(list: List<String>?): List<Language> {
        val map = list?.associateWith { it } ?: emptyMap()
        val resList = mutableListOf<Language>()
        this.forEach {
            resList.add(Language(it, map.containsKey(it)))
        }
        return resList
    }

    override fun translate(text: String, callback: (Result<Any>) -> Unit) {
        if (isDownloaded) {
            englishRussianTranslator.translate(text)
                .addOnCompleteListener {
                    it.result?.let { result ->
                        Result.Success.Value(result)
                    }
                }
                .addOnFailureListener {
                    Result.Failure.Error(it)
                }
        }
    }

    private fun checkIfDownload() {
        englishRussianTranslator.downloadModelIfNeeded(conditions)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    isDownloaded = it.isSuccessful
                } else {
                    val russianModel = TranslateRemoteModel.Builder(TranslateLanguage.RUSSIAN).build()
                    modelManager.download(russianModel, conditions)
                        .addOnSuccessListener {
                            isDownloaded = true
                        }
                        .addOnFailureListener {
                            isDownloaded = false
                        }
                }
            }
    }
}