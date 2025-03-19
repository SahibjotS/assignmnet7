package com.example.assignmnet7
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val names = ArrayList<String>()
    private val amounts = ArrayList<String>()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.expenseNameInput)
        val amountInput = findViewById<EditText>(R.id.expenseAmountInput)
        val addButton = findViewById<Button>(R.id.addExpenseButton)
        val recyclerView = findViewById<RecyclerView>(R.id.expenseRecyclerView)

        adapter = ExpenseAdapter(names, amounts) { position ->
            removeExpense(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val amount = amountInput.text.toString().trim()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(this, "Please enter both Name and Amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            names.add(name)
            amounts.add(amount)
            adapter.notifyDataSetChanged()

            nameInput.text.clear()
            amountInput.text.clear()
        }


    }

    private fun removeExpense(position: Int) {
        val deleted = names[position]
        Toast.makeText(this, "Deleted: $deleted", Toast.LENGTH_SHORT).show()
        names.removeAt(position)
        amounts.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}
