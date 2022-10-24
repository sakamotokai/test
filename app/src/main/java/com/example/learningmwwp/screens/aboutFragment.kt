package com.example.learningmwwp.screens

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.databinding.FragmentAboutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [aboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class aboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding
    lateinit var vm:ViewModelFragment
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    companion object{
        fun getNewInstance(args: Bundle):aboutFragment{
            val fragment=aboutFragment()
            fragment.arguments?.apply {
                getString("text")
                getInt("position")
            }
            fragment.arguments = args
            return fragment
        }
    }

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
    ): View {
        vm = ViewModelProvider(this).get(ViewModelFragment::class.java)
        binding = FragmentAboutBinding.inflate(layoutInflater,container,false)
        vm.pLiveData.observe(viewLifecycleOwner, Observer {
            binding.aboutFragmentTextView.text = it
        })
        val i = activity as MainActivity
        if(arguments?.getString("text")!!.isEmpty()){
                binding.fragmentAdd.setOnClickListener {
                    i.recAdapter.addElement(binding.aboutFragmentEditText.text.toString())
                }
        } else{
            binding.aboutFragmentTextView.text = arguments?.getString("text").toString()
            binding.fragmentAdd.setOnClickListener {
                vm.setMainData(i,binding.aboutFragmentEditText.text.toString(),arguments?.getInt("position")!!.toInt())
            }
        }

        return binding.root
    }


}