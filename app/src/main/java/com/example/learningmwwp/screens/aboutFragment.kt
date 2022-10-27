package com.example.learningmwwp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.R
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.checker
import com.example.learningmwwp.databinding.FragmentAboutBinding
import com.example.learningmwwp.db.Modeldb
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

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

    fun showToast(i: MainActivity) {
        Toast.makeText(i.applicationContext, "he he", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var mainActivity = activity as MainActivity
        viewModel = ViewModelProvider(this).get(aboutViewModel::class.java)
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        startFragment(mainActivity)
        /*if (arguments?.getString("text") != null) {
            viewModel.showDescription(
                Modeldb(
                    text = arguments?.getString("text")!!,
                    id = arguments?.getInt("key")!!
                ), binding, arguments
            )
        }
        binding.aboutAdd.setOnClickListener {
            viewModel.addLogic(
                Modeldb(id = viewModel.elementId(arguments),text = binding.aboutFragmentEditText.text.toString()),
                binding,arguments,mainActivity.recAdapter)
        }*/
        return binding.root
    }

    fun startFragment(mainActivity: MainActivity) {
        val dialog = BottomSheetDialog(binding.aboutAdd.context)
        dialog.setContentView(R.layout.fragment_about)
        val addBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutAdd)
        val deleteBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutDelete)
        val editText = dialog.findViewById<EditText>(R.id.aboutFragmentEditText)
        val textView = dialog.findViewById<TextView>(R.id.aboutFragmentTextView)
        if (arguments?.getString("text") != null) {
            viewModel.showDescription(
                Modeldb(
                    text = arguments?.getString("text")!!,
                    id = arguments?.getInt("key")!!
                ), binding, arguments, textView = textView!!, deleteBtn!!
            )
        }
        addBtn?.setOnClickListener {
            viewModel.addLogic(
                Modeldb(
                    id = viewModel.elementId(arguments),
                    text = editText!!.text.toString()//binding.aboutFragmentEditText.text.toString()
                ),
                binding, arguments, mainActivity.recAdapter, editText
            )
        }
        dialog.show()
    }
}