package com.tugraz.quizlet.frontend

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.tugraz.quizlet.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<StartFragment>(R.id.main_fragment_view)
            }
        }
    }

    fun onSettingsClicked(view: View) {
        val prevLanguage = LocaleHelper.getLocale(this)

        val newLanguage = if (prevLanguage == "en") "zh" else "en"

        LocaleHelper.saveLocale(this, newLanguage)
        LocaleHelper.setLocale(this, newLanguage)

        recreate();

        val toastText = getString(R.string.toast_language_change)
        val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun onPlusClicked(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        val addQuestionFragment = AddQuestionFragment()
        transaction.replace(R.id.main_fragment_view, addQuestionFragment)
        transaction.commit()
    }

    fun onProfileClicked(view: View) {
        val transaction = supportFragmentManager.beginTransaction();
        val accountFragment = AccountFragment()
        transaction.replace(R.id.main_fragment_view, accountFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        val transaction = supportFragmentManager.beginTransaction();
        val startFragment = StartFragment()
        transaction.replace(R.id.main_fragment_view, startFragment)
        transaction.commit()
    }


}