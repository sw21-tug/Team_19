package com.tugraz.quizlet.frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tugraz.quizlet.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionLoadingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionLoadingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_loading, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SplashActivity.requestHandler.fetchAllQuestionsAsyncAndSetRemainingQuestions(this::navigateToPlayFragment)
        //BackgroundGetQuestions().execute(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionLoadingFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionLoadingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun navigateToPlayFragment() {
        val transaction = parentFragmentManager.beginTransaction();
        val playFragment = PlayFragment()
        transaction.addToBackStack("Start-Play")
        transaction.hide(this)
        transaction.add(R.id.main_fragment_view, playFragment)
        transaction.commit()
    }
}