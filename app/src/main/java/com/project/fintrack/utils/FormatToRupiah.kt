package com.project.fintrack.utils

import java.text.NumberFormat
import java.util.Locale

fun formatToRupiah(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    return formatter.format(amount).replace("Rp", "Rp ").replace(",00", "")
}