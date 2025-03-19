package com.example.assignmnet7
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val names = ArrayList<String>()
    private val amounts = ArrayList<String>()
    private val dates = ArrayList<String>()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val dateInputLayout = findViewById<TextInputLayout>(R.id.dateInputLayout)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<Button>(R.id.addButton)

        adapter = ExpenseAdapter(names, amounts, dates) { position -> removeExpense(position) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        dateInputLayout.setEndIconOnClickListener {
            showDatePicker()
        }

        addButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val amount = amountEditText.text.toString().trim()
            val date = dateInputLayout.editText?.text.toString().trim()

            if (name.isEmpty() || amount.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            names.add(name)
            amounts.add(amount)
            dates.add(date)
            adapter.notifyDataSetChanged()

            // Clear input fields
            nameEditText.text.clear()
            amountEditText.text.clear()
            dateInputLayout.editText?.text?.clear()
        }
    }

    private fun removeExpense(position: Int) {
        val removedName = names[position]
        Toast.makeText(this, "Removed: $removedName", Toast.LENGTH_SHORT).show()
        names.removeAt(position)
        amounts.removeAt(position)
        dates.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                findViewById<TextInputLayout>(R.id.dateInputLayout).editText?.setText(date)
            },
            year, month, day
        )
        datePicker.show()
}