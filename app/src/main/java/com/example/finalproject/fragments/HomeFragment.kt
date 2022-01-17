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
    private lateinit var progressBar: ProgressBar
    private lateinit var userImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userSurname: TextView
    private lateinit var userPhoneNumber: TextView
    private lateinit var userEmailAddress: TextView
    private lateinit var mainLinearLayout: LinearLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var userPassword: TextView
    private lateinit var buttonShowPassword: Button



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        progressBar.visibility = View.VISIBLE
        registerListeners()

        database.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(UserInfo::class.java) ?: return

                userName.text = userInfo.name
                userSurname.text = userInfo.surname
                userPhoneNumber.text = userInfo.phoneNumber
                userEmailAddress.text = userInfo.email
                userPassword.text = userInfo.password

                Glide.with(this@HomeFragment)
                    .load(userInfo.imageUrl)
                    .into(userImage)


                progressBar.visibility = View.GONE
                mainLinearLayout.visibility = View.VISIBLE
                linearLayout.visibility = View.VISIBLE

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        buttonShowPassword.setOnClickListener{
            userPassword.setBackgroundResource(R.drawable.gradient_background)
        }

    }

    private fun init(){
        buttonLogout = view?.findViewById(R.id.buttonLogout)!!
        buttonPasswordChange = view?.findViewById(R.id.buttonPasswordChange)!!
        progressBar = view?.findViewById(R.id.progressBar)!!
        userName = view?.findViewById(R.id.userName)!!
        userSurname = view?.findViewById(R.id.userSurname)!!
        userPhoneNumber = view?.findViewById(R.id.userPhoneNumber)!!
        userEmailAddress = view?.findViewById(R.id.userEmailAddress)!!
        userImage = view?.findViewById(R.id.userImage)!!
        linearLayout = view?.findViewById(R.id.linearLayout)!!
        mainLinearLayout = view?.findViewById(R.id.mainLinearLayout)!!
        userPassword = view?.findViewById(R.id.userPassword)!!
        buttonShowPassword = view?.findViewById(R.id.buttonShowPassword)!!


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



    private fun hideKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}