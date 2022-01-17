package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavMenu)

        val controller = findNavController(R.id.nav_host_fragment)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.weatherFragment,
                R.id.map,
                R.id.friendsFragment

            )

        )


        setupActionBarWithNavController(controller,appBarConfig)
        navView.setupWithNavController(controller)

//        init()
//        registerListeners()
    }

//    private fun init(){
//        buttonLogout = findViewById(R.id.buttonLogout)
//        buttonPasswordChange = findViewById(R.id.buttonPasswordChange)
//    }

//    private fun registerListeners() {
//        buttonLogout.setOnClickListener{
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this,LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//        buttonPasswordChange.setOnClickListener{
//            val intent = Intent(this,PasswordChangeActivity::class.java)
//            startActivity(intent)
//        }
//    }
}