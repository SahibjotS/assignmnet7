package com.example.assignmnet7

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmnet7.R

class ExpenseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_details)

        val expenseName = intent.getStringExtra("expense_name")
        val expenseAmount = intent.getDoubleExtra("expense_amount", 0.0)
        val expenseDate = intent.getStringExtra("expense_date")
        val expenseCategory = intent.getStringExtra("expense_category")

        val nameTextView = findViewById<TextView>(R.id.expenseNameDetail)
        val amountTextView = findViewById<TextView>(R.id.expenseAmountDetail)
        val dateTextView = findViewById<TextView>(R.id.expenseDateDetail)
        val categoryTextView = findViewById<TextView>(R.id.expenseCategoryDetail)

        nameTextView.text = expenseName
        amountTextView.text = expenseAmount.toString()
        dateTextView.text = expenseDate
        categoryTextView.text = expenseCategory
    }
}
