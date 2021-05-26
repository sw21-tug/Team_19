package com.tugraz.quizlet.frontend

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.tugraz.quizlet.R
import kotlin.system.exitProcess

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment(), View.OnClickListener {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val startButton = view.findViewById<Button>(R.id.button_start)
        startButton.setOnClickListener(this)

        val exitButton = view.findViewById<Button>(R.id.button_exit)
        exitButton.setOnClickListener(this)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Start.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                StartFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.button_start -> {
                    val transaction = parentFragmentManager.beginTransaction();
                    val playFragment = QuestionLoadingFragment()
                    transaction.addToBackStack("Start-Play")
                    transaction.hide(this)
                    transaction.add(R.id.main_fragment_view, playFragment)
                    transaction.commit()
                }
                R.id.button_exit -> {
                    activity?.finish()
                    exitProcess(0)
                }
            }
        }
    }
}