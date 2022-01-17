package com.example.finalproject.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.finalproject.LoginActivity
import com.example.finalproject.PasswordChangeActivity
import com.example.finalproject.R
import com.example.finalproject.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class HomeFragment: Fragment(R.layout.fragment_one) {

    private val auth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")

    private lateinit var buttonLogout: Button
    private lateinit var buttonPasswordChange:Button
    private  lateinit var userImage: ImageView
    private lateinit var textViewUsername: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var editTextUsername: EditText
    private lateinit var editTextImageUrl: EditText
    private lateinit var buttonSave: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        init()
        registerListeners()

        database.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(UserInfo::class.java) ?: return

                textViewUsername.text = userInfo.userName

                Glide.with(this@HomeFragment)
                    .load(userInfo.imageUrl)
                    .into(userImage)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
        }, 2500)

        saveUserInfoListener()

    }

    private fun init(){
        buttonLogout = view?.findViewById(R.id.buttonLogout)!!
        buttonPasswordChange = view?.findViewById(R.id.buttonPasswordChange)!!
        userImage = view?.findViewById(R.id.userImage)!!
        textViewUsername = view?.findViewById(R.id.textViewUsername)!!
        editTextUsername = view?.findViewById(R.id.editTextUsername)!!
        editTextImageUrl = view?.findViewById(R.id.editTextImageUrl)!!
        buttonSave = view?.findViewById(R.id.buttonSave)!!
        progressBar = view?.findViewById(R.id.progressBar)!!

    }

    private fun registerListeners() {

        buttonLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        buttonPasswordChange.setOnClickListener{
            val intent = Intent(activity,PasswordChangeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserInfoListener(){
        buttonSave.setOnClickListener{



            val username = editTextUsername.text.trim().toString()
            val url = editTextImageUrl.text.trim().toString()

            if(username.isEmpty() || url.isEmpty()){
                Toast.makeText(activity, "Values mustn't be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            progressBar.visibility = View.VISIBLE

            val userInfo = UserInfo(username,url)
            hideKeyBoard()

            editTextUsername.setText("")
            editTextImageUrl.setText("")

            database.child(auth.currentUser?.uid!!).setValue(userInfo).addOnCompleteListener{
                progressBar.visibility = View.GONE
            }

        }
    }



    private fun hideKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}