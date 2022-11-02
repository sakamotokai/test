package com.example.learningmwwp.RecyclerAdapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.R
import com.example.learningmwwp.checker
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import com.example.learningmwwp.globalDao
import com.example.learningmwwp.screens.aboutFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.LinkedList

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    private val elementList: MutableList<Modeldb> = LinkedList()

    fun setList(list: List<Modeldb>) {
        elementList.clear()
        elementList.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(text: String) {
        MainScope().launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(Modeldb(id = elementList.count(), text = text))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(modeldb: Modeldb) {
        elementList[modeldb.id - 1] = modeldb
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<TextView>(R.id.recyclerTextView)
        fun bind(elem: Modeldb) {
            text.text = elem.text
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
        setListener(holder, position)
        holder.bind(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.count()
    }

    fun getModeldb(): Modeldb {
        return elementList[itemCount]
    }

    fun setListener(view: ViewHolder, position: Int) {
        view.itemView.setOnClickListener {
            val dialog = BottomSheetDialog(view.itemView.context)
            dialog.setContentView(R.layout.fragment_about)
            val addBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutAdd)
            val editText = dialog.findViewById<EditText>(R.id.aboutFragmentEditText)
            val deleteBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutDelete)
            val textView = dialog.findViewById<TextView>(R.id.aboutFragmentTextView)
            textView!!.text = elementList[position].text
            addBtn!!.setOnClickListener {
                MainScope().launch(Dispatchers.IO) {
                    RepositoryRealization(globalDao).update(
                        Modeldb(
                            id = elementList[position].id,
                            text = editText!!.text.toString()
                        )
                    )
                }
                textView.text = editText!!.text.toString()
            }
            deleteBtn!!.setOnClickListener {
                MainScope().launch(Dispatchers.IO) {
                    RepositoryRealization(globalDao).delete(
                        Modeldb(
                            id = elementList[position].id,
                            text = elementList[position].text
                        )
                    )
                }
                dialog.hide()
            }
            dialog.show()
        }
    }
}
