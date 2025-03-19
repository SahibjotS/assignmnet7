package com.example.assignmnet7
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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

    override fun onStart() { super.onStart(); Log.d("MainActivity", "onStart Called") }
    override fun onResume() { super.onResume(); Log.d("MainActivity", "onResume Called") }
    override fun onPause() { super.onPause(); Log.d("MainActivity", "onPause Called") }
    override fun onStop() { super.onStop(); Log.d("MainActivity", "onStop Called") }
    override fun onDestroy() { super.onDestroy(); Log.d("MainActivity", "onDestroy Called") }




}

    private fun removeExpense(position: Int) {
        val deleted = names[position]
        Toast.makeText(this, "Deleted: $deleted", Toast.LENGTH_SHORT).show()
        names.removeAt(position)
        amounts.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}
