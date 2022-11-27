package com.example.spinnerrecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.spinnerrecycler.databinding.FragmentLoginBinding

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
             else {
                Toast.makeText(context,"Admin Hasn't lodged in",Toast.LENGTH_SHORT).show()
            }
        }
        fun fragmentNav(fragment: Fragment) {

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView2,fragment)
            fragmentTransaction?.commit()
        }

        loginbtn.setOnClickListener{
            if ((loginName == null) && (loginpass == null)) {
                Toast.makeText(context,"Please Add Credentials",Toast.LENGTH_SHORT).show()

            } else {
                login(loginName.toString(),loginpass.toString().toInt())
            }

            fragmentNav(FragmentEntry())

        }

        btnClear.setOnClickListener{
            loginName.clear()
            loginpass.clear()
        }


    }

}
