package com.rojek.projekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment() {
    companion object {
        const val ARG_NAME = "name"


        fun newInstance(name: String): MenuFragment {
            val fragment = MenuFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }

            fragment.arguments = bundle

            return fragment
        }
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_menu, container, false)


        view.settingButton.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_webFragment)
        }

        return view
    }

}