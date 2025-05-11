package com.nicholas.vaultpay.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.nicholas.vaultpay.R

@Composable
fun BillPaymentIcon(billType: String, modifier: Modifier = Modifier) {
    val iconRes = when (billType.lowercase()) {
        "electricity" -> R.drawable.ic_electricity
        "water" -> R.drawable.ic_water
        "internet" -> R.drawable.ic_internet
        "insurance" -> R.drawable.ic_insurance
        "phone" -> R.drawable.ic_phone
        else -> R.drawable.ic_default_bill
    }

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = "$billType icon",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}


