package com.aashish.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.braintreepayments.cardform.view.CardForm

class MainActivity : AppCompatActivity() {

    private lateinit var cardForm: CardForm
    private lateinit var btnMakePayment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardForm = findViewById(R.id.credit_card_page)
        btnMakePayment = findViewById(R.id.btnMakePayment)

        cardForm.cardRequired(true)
            .cvvRequired(true)
            .expirationRequired(true)
            .cardholderName(CardForm.FIELD_REQUIRED)
            .setup(this@MainActivity)

        btnMakePayment.setOnClickListener {
            confirmingPayment()
        }
    }

    private fun confirmingPayment() {
        if (!cardForm.isValid || cardForm.cardNumber.isEmpty() || cardForm.cvv.isEmpty() || cardForm.expirationMonth.isEmpty() || cardForm.expirationYear.isEmpty()
            || cardForm.cardholderName.isEmpty()){

            Toast.makeText(this, "please enter all the valid fields", Toast.LENGTH_SHORT).show()

        }else{

            //creating the pop up confirmation for successful transaction
            val alert = AlertDialog.Builder(this@MainActivity)
            alert.setTitle("PAYMENT SUCCESSFUL")
            alert.setIcon(R.drawable.ic_confirm)
            alert.setMessage("ThankYou")
            alert.setPositiveButton("Ok"){dialogInterface, which ->

                cardForm.cardholderNameEditText.text?.clear()
                cardForm.cardEditText.text?.clear()
                cardForm.expirationDateEditText.text?.clear()
                cardForm.cvvEditText.text?.clear()


            }

            alert.setCancelable(false)
            alert.create()
            alert.show()
        }
    }

}