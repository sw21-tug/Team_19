package com.tugraz.quizlet.frontend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.tugraz.quizlet.R
import java.util.*

class StartActivity : AppCompatActivity()
{
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

    override fun onBackPressed() {
        val transaction = supportFragmentManager.beginTransaction();
        val startFragment = StartFragment()
        //transaction.hide(this)
        transaction.replace(R.id.main_fragment_view, startFragment)
        transaction.commit()
    }
}