package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")


    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var buttonRegister: Button
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextImageUrl: EditText

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
        editTextName = findViewById(R.id.editTextName)
        editTextSurname = findViewById(R.id.editTextSurname)
        editTextImageUrl = findViewById(R.id.editTextImageUrl)

    }

    private fun registerListeners(){
        buttonRegister.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()
            val name = editTextName.text.trim().toString()
            val surname = editTextSurname.text.trim().toString()
            val image = editTextImageUrl.text.trim().toString()

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

            if(name.isEmpty()){
                Toast.makeText(this, "Name field is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(surname.isEmpty()){
                Toast.makeText(this, "Name field is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(image.isEmpty()){
                Toast.makeText(this, "Image field is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val userInfo = UserInfo(name,surname,phoneNumber,email, image,password)
                        database.child(auth.currentUser?.uid!!).setValue(userInfo)
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