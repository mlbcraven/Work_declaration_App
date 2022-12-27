package com.example.spinnerrecycler

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.spinnerrecycler.databinding.FragmentLoginBinding

@Suppress("DEPRECATION")
class LoginFragment:Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val loginName = binding.edWorker.text
         val loginpass = binding.edPassword.text
         val loginbtn = binding.btnLogin
         val btnClear = binding.btnClear

        fun login(string: String, int: Int ) {
            if ((string == "pda2") && (int == 2)){
                Toast.makeText(context,"Welcome Admin",Toast.LENGTH_SHORT).show()


            }
                else if ((string == "pda3") && (int == 3 )) {
                 Toast.makeText(context,"Welcome User 3", Toast.LENGTH_SHORT).show()

            }
                else if ((string == "Guest") && (int == 1234)) {
                Toast.makeText(context,"Welcome Guest", Toast.LENGTH_SHORT).show()
            }
                else {
                Toast.makeText(context,"User Hasn't lodged in",Toast.LENGTH_SHORT).show()
            }
        }
        fun fragmentNav(fragment: Fragment) {

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView2,fragment)
            fragmentTransaction?.commit()
        }

        loginbtn.setOnClickListener{
            if ((loginName.isEmpty()) || (loginpass.isEmpty())) {
                Toast.makeText(context,"Please Add Credentials",Toast.LENGTH_SHORT).show()

            } else {
                login(loginName.toString(),loginpass.toString().toInt())
                fragmentNav(FragmentEntry())
            }



        }

        btnClear.setOnClickListener{
            loginName.clear()
            loginpass.clear()
        }


    }

}

