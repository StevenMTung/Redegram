package br.com.steventung.redegram.mlkit

import br.com.steventung.redegram.domain.model.Language
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class TextTranslate {

    fun identifyLanguage(
        text: String,
        onSuccess: (Language) -> Unit = {},
        onFailure: () -> Unit = {}
    ) {
        val languageIdentifier = LanguageIdentification.getClient()
        languageIdentifier.identifyLanguage(text)
            .addOnSuccessListener { languageCode ->
                val languageName =
                    translatableLanguageModels[languageCode] ?: UNIDENTIFIED_LANGUAGE_TAG

                if (languageName != UNIDENTIFIED_LANGUAGE_TAG) {
                    onSuccess(Language(languageName = languageName, languageCode = languageCode))
                } else {
                    onFailure()
                }
            }
            .addOnFailureListener {
                onFailure()
            }
    }

    fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
        onSuccess: (String) -> Unit = {},
        onFailure: () -> Unit = {}
    ) {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguage)
            .setTargetLanguage(targetLanguage)
            .build()
        val translator = Translation.getClient(options)

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        onSuccess(translatedText)
                    }
                    .addOnFailureListener {
                        onFailure()
                    }
            }
            .addOnFailureListener {
                onFailure()
            }
    }
}