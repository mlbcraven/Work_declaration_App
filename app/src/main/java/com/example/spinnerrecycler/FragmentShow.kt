package com.example.spinnerrecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spinnerrecycler.databinding.FragmentEntryBinding
import com.example.spinnerrecycler.databinding.FragmentShowBinding
import com.example.spinnerrecycler.db.Entry
import com.example.spinnerrecycler.db.SQLiteHelper

class FragmentShow: Fragment(R.layout.fragment_show) {
    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var sqLiteHelper: SQLiteHelper
    private var adapter: EntryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentShowBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sqLiteHelper = SQLiteHelper(requireActivity() as MainActivity)

        initRecyclerView()
        getEntries()
        adapter?.notifyDataSetChanged()
        getEntries()
        adapter?.onClickDeleteItem { deleteEntry(it.id) }



    }
    private fun deleteEntry(id : Int) {
        if (id == null) return
        val builder = AlertDialog.Builder(this.requireActivity())
        builder.setMessage("Θελετε να διαγραψετε την επιλεγμενη καταχωρηση??")
        builder.setCancelable(true)
        builder.setPositiveButton("Ναι"){
            dialog, _ ->
            sqLiteHelper.deleteEntryById(id)
            getEntries()
            dialog.dismiss()
        }
        builder.setNegativeButton("Οχι")
        {dialog, _ -> dialog.dismiss()}
        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerView() {
        binding.recyclerviewID.layoutManager = LinearLayoutManager(requireActivity() as MainActivity)
        adapter = EntryAdapter()
        binding.recyclerviewID.adapter = adapter



    }

    private fun getEntries() {
        val entrylist = sqLiteHelper.getEntry()
        adapter?.addItems(entrylist)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        return
    }

}