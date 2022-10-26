package com.example.learningmwwp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.checker
import com.example.learningmwwp.databinding.FragmentAboutBinding
import com.example.learningmwwp.db.Modeldb

class aboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding
    lateinit var viewModel: aboutViewModel

    companion object {
        fun setBundle(bundle: Bundle): aboutFragment {
            var fragment = aboutFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(aboutViewModel::class.java)
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        if(checker){viewModel.showDescription(
            Modeldb(
                text = arguments?.getString("text",)!!,
                id = arguments?.getInt("key",)!!
            ), binding, arguments
        )}
        binding.fragmentAdd.setOnClickListener {
            viewModel.addLogic(
                Modeldb(text = binding.aboutFragmentEditText.text.toString()),
                binding,
                arguments
            )
        }
        return binding.root
    }

    override fun onDestroy() {
        checker = false
        super.onDestroy()
    }
}