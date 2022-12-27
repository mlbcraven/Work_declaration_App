package com.example.spinnerrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.spinnerrecycler.databinding.ActivityMainBinding
import com.example.spinnerrecycler.databinding.FragmentLoginBinding
import com.example.spinnerrecycler.db.SQLiteHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EntryAdapter
    private lateinit var sqLiteHelper: SQLiteHelper

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