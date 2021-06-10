package com.tugraz.quizlet.frontend

import android.os.AsyncTask
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tugraz.quizlet.R
import kotlinx.coroutines.delay

class  BackgroundGetQuestions : AsyncTask<Any, Any, Any>()
{

    override fun doInBackground(vararg params: Any?) {
        Thread.sleep(3000)
        var fragment = params[0] as Fragment
        val transaction = fragment.parentFragmentManager.beginTransaction();
        val playFragment = PlayFragment()
        transaction.addToBackStack("Start-Play")
        transaction.hide(fragment)
        transaction.add(R.id.main_fragment_view, playFragment)
        transaction.commit()
    }


}