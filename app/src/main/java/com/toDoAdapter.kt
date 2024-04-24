package com.elifnuroksuz.odev2024

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val itemList: MutableList<Item>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = itemList[position]
                    onDeleteButtonClick(item, position)
                }
            }
        }
    }

    private fun onDeleteButtonClick(item: Item, position: Int) {
        itemList.remove(item)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
    }

    override fun getItemCount() = itemList.size
}
