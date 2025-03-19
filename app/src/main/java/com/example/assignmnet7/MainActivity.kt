package com.example.assignmnet7
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    private val expenses = ArrayList<Expense>()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "onCreate called")

        val nameInput = findViewById<EditText>(R.id.expenseNameInput)
        val amountInput = findViewById<EditText>(R.id.expenseAmountInput)
        val dateInput = findViewById<EditText>(R.id.expenseDateInput)
        val categoryInput = findViewById<EditText>(R.id.expenseCategoryInput)
        val addButton = findViewById<Button>(R.id.addExpenseButton)
        val recyclerView = findViewById<RecyclerView>(R.id.expenseRecyclerView)

        adapter = ExpenseAdapter(expenses) { position ->
            showExpenseDetails(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val amountText = amountInput.text.toString().trim()
            val date = dateInput.text.toString().trim()
            val category = categoryInput.text.toString().trim()

            if (name.isEmpty() || amountText.isEmpty() || date.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDouble()
            val expense = Expense(name, amount, date, category)

            expenses.add(expense)
            adapter.notifyDataSetChanged()

            nameInput.text.clear()
            amountInput.text.clear()
            dateInput.text.clear()
            categoryInput.text.clear()
        }
    }

    private fun showExpenseDetails(position: Int) {
        val expense = expenses[position]

        val intent = Intent(this, ExpenseDetailsActivity::class.java).apply {
            putExtra("expense_name", expense.name)
            putExtra("expense_amount", expense.amount)
            putExtra("expense_date", expense.date)
            putExtra("expense_category", expense.category)
        }
        startActivity(intent)
    }
}