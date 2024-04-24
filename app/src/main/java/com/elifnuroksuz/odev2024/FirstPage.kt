package com.elifnuroksuz.odev2024

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstPage : Fragment() {
    private val itemList = mutableListOf<Item>()
    private lateinit var adapter: ToDoAdapter

    @SuppressLint("NotifyDataSetChanged")


    fun onDeleteButtonClick(position: Int) {
        itemList.removeAt(position)
        adapter.notifyDataSetChanged()
        Toast.makeText(context, "Öğe silindi", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_page, container, false)

        val button = view.findViewById<Button>(R.id.buttonAdd)
        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = ToDoAdapter(itemList)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val text1 = editTextTitle.text.toString()
            val text2 = editTextDescription.text.toString()

            if (text1.isNotEmpty() && text2.isNotEmpty()) {
                val newItem = Item(itemList.size + 10, text1, text2)
                itemList.add(newItem)
                adapter.notifyDataSetChanged()
                editTextTitle.setText("")
                editTextDescription.setText("")
            } else {
                Toast.makeText(context, "Fill all the blanks", Toast.LENGTH_SHORT).show()
                editTextTitle.setText("")
                editTextDescription.setText("")
            }
        }

        return view
    }
}