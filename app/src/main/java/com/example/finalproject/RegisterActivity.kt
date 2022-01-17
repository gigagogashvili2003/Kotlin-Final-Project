package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var buttonRegister: Button
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerListeners()
    }

    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        buttonRegister = findViewById(R.id.buttonRegister)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
    }

    private fun registerListeners(){
        buttonRegister.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            if(email.trim().length < 6 || !email.contains("@")){
                Toast.makeText(this,"Email Must be length of 6 or more, and also must contain @", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.trim().length < 6 || password[0].isLowerCase()){
                Toast.makeText(this,"Password must be length of 6 or more, and first letter must be Capitalze",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(repeatPassword != password){
                Toast.makeText(this,"Make sure the both password values are same!",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(phoneNumber.trim().length < 9){
                Toast.makeText(this,"Write Legal Phone Number!",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(!radioMale.isChecked && !radioFemale.isChecked){
                Toast.makeText(this,"Gender must be selected!",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this,"Account Created Succesfully, please loggin", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Something Went Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}