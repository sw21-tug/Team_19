package com.tugraz.quizlet.frontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tugraz.quizlet.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SCORE = "score"
private const val ARG_HIGHSCORE= "highscore"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var score: Int? = null
    private var highscore: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            score = it.getInt(ARG_SCORE)
            highscore = it.getInt(ARG_HIGHSCORE)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_score, container, false)

        val scoreTextView = view.findViewById<TextView>(R.id.text_view_score)
        scoreTextView.text = score?.toString()

        val highScoreTextView = view.findViewById<TextView>(R.id.text_view_high_score)
        highScoreTextView.text = highscore?.toString()
        val playAgainButton = view.findViewById<Button>(R.id.play_again)
        playAgainButton.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction();
            val playFragment = PlayFragment()
            transaction.addToBackStack("Score-Play")
            transaction.hide(this)
            transaction.replace(R.id.main_fragment_view, playFragment)
            transaction.commit()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ScoreFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_SCORE, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}