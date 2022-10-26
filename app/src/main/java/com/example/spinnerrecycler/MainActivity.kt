package com.example.spinnerrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spinnerrecycler.databinding.ActivityMainBinding
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

        binding.btnFirstFragment.setOnClickListener {
            fragmentNav(FragmentEntry())
        }


        binding.btnSecondFragment.setOnClickListener {
            fragmentNav(FragmentShow())

        }

    }

    private fun fragmentNav(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2,fragment)
        fragmentTransaction.commit()
    }
}