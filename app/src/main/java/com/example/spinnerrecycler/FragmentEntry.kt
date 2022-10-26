package com.example.spinnerrecycler


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import androidx.fragment.app.Fragment


import androidx.viewbinding.ViewBinding
import com.example.spinnerrecycler.databinding.FragmentEntryBinding
import com.example.spinnerrecycler.db.Entry
import com.example.spinnerrecycler.db.SQLiteHelper
import java.util.*

class FragmentEntry:Fragment(R.layout.fragment_entry) {
    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!
    var WorkerSelected = ""
    var WorkSelected = ""
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var adapter: EntryAdapter

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        _binding = FragmentEntryBinding.inflate(inflater,container,false)
        val view = binding.root
        val Worker :Spinner = binding.spnWorker
        ArrayAdapter.createFromResource(requireActivity() as MainActivity,R.array.Worker,android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                Worker.adapter = adapter}
        Worker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapteView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                WorkerSelected = adapteView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }




    }
        val Work:Spinner = binding.spnWorkList
        ArrayAdapter.createFromResource(requireActivity() as MainActivity,R.array.Worklist, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Work.adapter = adapter}
        Work.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapteView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                WorkSelected = adapteView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val entrybtn = binding.btnEntry
        entrybtn.setOnClickListener {
            addEntry()
        }

        sqLiteHelper = SQLiteHelper(requireActivity() as MainActivity)
        return view

}
    private fun addEntry() {
        val worker = WorkerSelected
        val work = WorkSelected
        val problems = binding.EditProblemsId.text.toString()
        val instance = getCurrentDateTime().toString()

        if (worker.isEmpty() || work.isEmpty()) {
            Toast.makeText(requireActivity() as MainActivity,"Παρακαλω Επιλεξτε Εργασια και Εργαζομενο", Toast.LENGTH_SHORT).show()
        }else {
            val ent = Entry(name = worker, work = work, timestamp = instance, problems = problems)
            val status = sqLiteHelper.addEntry(ent)

            if (status > -1) {
                Toast.makeText(requireActivity() as MainActivity, "Η καταχωρηση ηταν επιτυχης", Toast.LENGTH_SHORT).show()
                binding.EditProblemsId.setText("")
            }else{
                Toast.makeText(requireActivity() as MainActivity, "Η καταχωρηση σας Δεν ηταν επιτυχης", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getEntries() {
        val entrylist = sqLiteHelper.getEntry()
        adapter?.addItems(entrylist)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        return
    }}



