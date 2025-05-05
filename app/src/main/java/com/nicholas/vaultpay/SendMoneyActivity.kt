package com.nicholas.vaultpay

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SendMoneyActivity : AppCompatActivity() {

    private lateinit var recipientEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)

        // Initialize views
        recipientEditText = findViewById(R.id.editTextRecipient)
        amountEditText = findViewById(R.id.editTextAmount)
        noteEditText = findViewById(R.id.editTextNote)
        sendButton = findViewById(R.id.btnSendMoney)
        cancelButton = findViewById(R.id.btnCancel)

        // Handle send button click
        sendButton.setOnClickListener {
            val recipient = recipientEditText.text.toString().trim()
            val amount = amountEditText.text.toString().trim()

            if (recipient.isEmpty()) {
                recipientEditText.error = "Enter recipient"
                return@setOnClickListener
            }

            if (amount.isEmpty()) {
                amountEditText.error = "Enter amount"
                return@setOnClickListener
            }

            // You can add real logic to process payment here (e.g., API call)
            Toast.makeText(this, "Sent Ksh $amount to $recipient", Toast.LENGTH_LONG).show()
        }

        // Handle cancel button click
        cancelButton.setOnClickListener {
            finish() // Close the activity
        }
    }
}
