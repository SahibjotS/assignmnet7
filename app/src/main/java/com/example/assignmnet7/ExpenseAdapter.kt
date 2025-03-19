package com.example.assignmnet7
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val expenses: List<Expense>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.nameTextView.text = expense.name
        holder.amountTextView.text = expense.amount.toString()
        holder.dateTextView.text = expense.date
        holder.categoryTextView.text = expense.category


        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount() = expenses.size

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.expenseName)
        val amountTextView: TextView = itemView.findViewById(R.id.expenseAmount)
        val dateTextView: TextView = itemView.findViewById(R.id.expenseDate)
        val categoryTextView: TextView = itemView.findViewById(R.id.expenseCategory)
    }
}
