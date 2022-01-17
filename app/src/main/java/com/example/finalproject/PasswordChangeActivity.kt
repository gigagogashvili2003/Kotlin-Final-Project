package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonChange: Button
    private lateinit var editTextNewPasswordRepeat: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()

        registerListeners()
    }

    private fun init(){

        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        editTextNewPasswordRepeat = findViewById(R.id.editTextNewPasswordRepeat)
        buttonChange = findViewById(R.id.buttonChange)

    }

    private fun registerListeners(){
        buttonChange.setOnClickListener{

            val newPassword = editTextNewPassword.text.toString()
            val newPasswordRepeat = editTextNewPasswordRepeat.text.toString()
            if(newPassword.length < 6 || newPassword[0].isLowerCase()){
                Toast.makeText(this,"Password must be length of 6 or more, and first letter must be Capitalze",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if(newPasswordRepeat != newPassword){
                Toast.makeText(this,"Passwords must be same!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"Password Changed Succesfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Something Went Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}