package com.project.fintrack.presentation.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.presentation.viewmodels.CreateTransactionViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateTransactionScreen(viewModel: CreateTransactionViewModel) {
    var transactionType by remember { mutableStateOf("Spending") }
    var category by remember { mutableStateOf("Entertainment") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var isTypeDropdownExpanded by remember { mutableStateOf(false) }
    var isCategoryDropdownExpanded by remember { mutableStateOf(false) }

    // Context for DatePickerDialog
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State for Snackbar
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Transaction",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )

        // Transaction Type Dropdown
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { isTypeDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = transactionType)
            }
            DropdownMenu(
                expanded = isTypeDropdownExpanded,
                onDismissRequest = { isTypeDropdownExpanded = false }
            ) {
                listOf("Spending", "Income").forEach { type ->
                    DropdownMenuItem(
                        text = { Text(text = type) },
                        onClick = {
                            transactionType = type
                            isTypeDropdownExpanded = false
                        }
                    )
                }
            }
        }

        // Category Dropdown
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { isCategoryDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = category)
            }
            DropdownMenu(
                expanded = isCategoryDropdownExpanded,
                onDismissRequest = { isCategoryDropdownExpanded = false }
            ) {
                listOf("Entertainment", "Food", "Transportation", "Other").forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(text = cat) },
                        onClick = {
                            category = cat
                            isCategoryDropdownExpanded = false
                        }
                    )
                }
            }
        }

        // Amount Input
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Date Input with Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                modifier = Modifier.weight(1f),
                readOnly = true // Prevent direct editing
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    showDatePicker(context, calendar) { selectedDate ->
                        date = selectedDate
                    }
                }
            ) {
                Text("Change")
            }
        }

        // Notes Input
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        // Save Button
        Button(
            onClick = {
                // Logic to save transaction
                if(transactionType == "Spending"){
                    transactionType = "EXPENSE"
                } else{
                    transactionType = "INCOME"
                }
                println("Transaction Saved: Type=$transactionType, Category=$category, Amount=$amount, Date=$date, Notes=$notes")
                val transaction = TransactionEntity(
                    type = TransactionType.valueOf(transactionType),
                    category = TransactionCategory.valueOf(category.toUpperCase()),
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    date = Date(),
                    description = notes, // Pastikan nama sesuai dengan model
                    id = 0
                )
                viewModel.createTransactions(transaction)
                Toast.makeText(context, "Transaction Saved Successfully", Toast.LENGTH_SHORT).show()
                // Clear the form
                transactionType = "Spending"
                category = "Entertainment"
                amount = ""
                date = ""
                notes = ""

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Save")
        }
    }
}

// Function to show DatePickerDialog
fun showDatePicker(
    context: Context,
    calendar: Calendar,
    onDateSelected: (String) -> Unit
) {
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val selectedDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar.time)
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
