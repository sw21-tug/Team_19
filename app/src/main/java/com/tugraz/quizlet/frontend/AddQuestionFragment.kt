package com.tugraz.quizlet.frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.R
import com.tugraz.quizlet.backend.database.model.Question_category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddQuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val view = inflater.inflate(R.layout.fragment_add_question, container, false)
        val button = view.findViewById<Button>(R.id.submitQuestion)
        button.setOnClickListener(View.OnClickListener {
            readFieldAndAddQuestionToDatabase(view)
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddQuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddQuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun readFieldAndAddQuestionToDatabase(view: View?) {
        if (view != null) {
            val category = view.findViewById<Spinner>(R.id.spinner).selectedItem.toString()
            val question = view.findViewById<EditText>(R.id.editTextQuestion).text.toString()
            val rightAnswer = view.findViewById<EditText>(R.id.editTextRightAnswer).text.toString()
            val wrongAnswer1 = view.findViewById<EditText>(R.id.editTextWrong1).text.toString()
            val wrongAnswer2 = view.findViewById<EditText>(R.id.editTextWrong2).text.toString()
            val wrongAnswer3 = view.findViewById<EditText>(R.id.editTextWrong3).text.toString()

            if (category.isEmpty() || question.isEmpty() || rightAnswer.isEmpty() || wrongAnswer1.isEmpty() || wrongAnswer2.isEmpty() || wrongAnswer3.isEmpty()) {
                Toast.makeText(
                    view.context,
                    R.string.app_start_add_question_error,
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val questionCategory = Question_category(null, category)
            val wrongAnswers = ImmutableList.of(wrongAnswer1, wrongAnswer2, wrongAnswer3)

            SplashActivity.requestHandler.addQuestion(
                questionCategory,
                question,
                rightAnswer,
                wrongAnswers
            )
        }
    }
}