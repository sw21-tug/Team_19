package com.tugraz.quizlet.frontend

import android.content.Context
import java.util.*

object LocaleHelper {
    private const val LANGUAGE = "Locale.Language"

    fun setLocale(context: Context, language: String) : Context {
        val newLocale = Locale(language)
        Locale.setDefault(newLocale)

        val configuration = context.resources.configuration
        configuration.setLocale(newLocale)
        configuration.setLayoutDirection(newLocale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)

//      return context.createConfigurationContext(configuration);
        return context
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