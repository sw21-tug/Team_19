package com.tugraz.quizlet.frontend

import android.content.Context
import com.tugraz.quizlet.R
import java.util.*

public object LocaleHelper {
    private const val LANGUAGE = "Locale.Language"

    fun setLocale(context: Context, language: String) : Context {
        val newLocale = Locale(language)
        Locale.setDefault(newLocale)

        val configuration = context.resources.configuration
        configuration.setLocale(newLocale)
        configuration.setLayoutDirection(newLocale)

        return context.createConfigurationContext(configuration);
    }

    fun saveLocale(context: Context, language: String) {
        val sharedPref = context.getSharedPreferences("main", Context.MODE_PRIVATE) ?: return

        with (sharedPref.edit()) {
            putString(LANGUAGE, language)
            apply()
        }
    }

    fun getLocale(context: Context) : String {
        val sharedPref = context.getSharedPreferences("main", Context.MODE_PRIVATE)

        return if (sharedPref != null) {
            val language = sharedPref.getString(
                LANGUAGE,
                Locale.getDefault().language
            )

            language!!
        } else {
            Locale.getDefault().language
        }
    }
}