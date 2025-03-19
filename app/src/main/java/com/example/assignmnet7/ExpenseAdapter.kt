package com.example.assignmnet7;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList;

class ExpenseAdapter (

    private val names: ArrayList<String>,
    private val amounts: ArrayList<String>,
    private val dates: ArrayList<String>,
    private val delete: (Int) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTextView)
        val amount: TextView = view.findViewById(R.id.amountTextView)
        val date: TextView = view.findViewById(R.id.dateTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.name.text = names[position]
        holder.amount.text = "$${amounts[position]}"
        holder.date.text = dates[position]
        holder.deleteButton.setOnClickListener { delete(position) }
    }

    override fun getItemCount(): Int = names.size
}