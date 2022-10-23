package com.example.learningmwwp.RecyclerAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.R
import com.example.learningmwwp.screens.aboutFragment
import java.util.LinkedList

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    private val elementList: MutableList<String> = LinkedList()

    fun setData(element: List<String>) {
        elementList.clear()
        elementList.addAll(element)
        notifyDataSetChanged()
    }

    //пока что делаем радикально, позже исправить
    fun addElement(elem:String){
        elementList.add(elem)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<TextView>(R.id.recyclerTextView)
        fun bind(elem: String) {
            text.text = elem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_element, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setListener(holder,position)
        holder.bind(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.count()
    }

    fun setListener(view:ViewHolder,position: Int){
        val replace = view.itemView.context as AppCompatActivity
        val i = view.itemView.context as MainActivity
        view.itemView.setOnClickListener {
            i.binding.mainText.text = elementList[position]
            val bundle = Bundle().apply {
                putString("text",elementList[position])
                putInt("position",position)
            }
            replace.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer
                ,aboutFragment.getNewInstance(bundle),"aboutFragment").commit()
        }
    }
}