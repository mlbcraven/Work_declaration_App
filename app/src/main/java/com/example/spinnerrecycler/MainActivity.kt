package com.example.spinnerrecycler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.spinnerrecycler.databinding.ActivityMainBinding
import com.example.spinnerrecycler.databinding.FragmentLoginBinding
import com.example.spinnerrecycler.db.SQLiteHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var sqLiteHelper: SQLiteHelper
    companion object{
        var loginUser = ""
        var onWork: Boolean = false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sqLiteHelper = SQLiteHelper(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentNav(LoginFragment())

        binding.btnFirstFragment.setOnClickListener {
            fragmentNav(LoginFragment())
        }


        binding.btnSecondFragment.setOnClickListener {
            fragmentNav(FragmentShow())
        }

    }

      fun fragmentNav(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2,fragment)
        fragmentTransaction.commit()
    }
}