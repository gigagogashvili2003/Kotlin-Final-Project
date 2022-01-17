package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        init()

        registerListeners()
    }

    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSend = findViewById(R.id.buttonSend)
    }

    private fun registerListeners(){
        buttonSend.setOnClickListener{

            val email = editTextEmail.text.toString()

            if(email.length < 6 || !email.contains("@")){
                Toast.makeText(this,"Email Must be length of 6 or more, and also must contain @", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"You have sent reset password instructions on email!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Something Went Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}