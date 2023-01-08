package com.example.spinnerrecycler


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.spinnerrecycler.MainActivity.Companion.loginUser
import com.example.spinnerrecycler.MainActivity.Companion.onWork

import com.example.spinnerrecycler.databinding.ActivityMainBinding
import com.example.spinnerrecycler.databinding.FragmentEntryBinding
import com.example.spinnerrecycler.databinding.FragmentShowBinding
import com.example.spinnerrecycler.db.Entry
import com.example.spinnerrecycler.db.SQLiteHelper
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME
import java.util.*
import java.util.logging.SimpleFormatter
import kotlin.concurrent.thread


class FragmentEntry:Fragment(R.layout.fragment_entry) {
    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!
    var WorkSelected = ""
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var adapter: EntryAdapter



    fun getCurrentDateTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)
        return current

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        _binding = FragmentEntryBinding.inflate(inflater,container,false)

        //Switch
             val switch = binding.switch1
             val workStatus = binding.workStatus
             workStatus.text = "$loginUser is Idle"

             switch.setOnCheckedChangeListener { _, isChecked ->

                 if (isChecked) {
                     workStatus.text = "$loginUser is Currently Working On"
                     onWork = true


                 } else {
                     onWork = false
                     workStatus.text = "$loginUser is Idle"


                 }
             }


        val view = binding.root
     //Work Spinner
        val Work:Spinner = binding.spnWorkList
        ArrayAdapter.createFromResource(requireActivity() as MainActivity,R.array.Worklist, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Work.adapter = adapter}
        Work.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapteView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                WorkSelected = adapteView?.getItemAtPosition(position).toString()
                val image = binding.imageView
                if (WorkSelected == "Picking") {

                    image.setImageResource(R.drawable.ic_baseline_picking)
                }else if (WorkSelected == "Ετικετοκολληση") {
                    image.setImageResource(R.drawable.ic_baseline_auto_awesome_motion_24)
                }else if ((WorkSelected == "Εμφιαλωση") || (WorkSelected == "Προετοιμασια")) {
                    image.setImageResource(R.drawable.ic_baseline_update_24)
                }else{
                    image.setImageResource(R.drawable.ic_baseline_assignment_24)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        val entrybtn = binding.btnEntry
        entrybtn.setOnClickListener {
            addEntry()
            thread {
            onWork = false
            }
        }

        sqLiteHelper = SQLiteHelper(requireActivity() as MainActivity)


        return view


}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun addEntry() {
        val worker = loginUser
        val work = WorkSelected
        val problems = binding.EditProblemsId.text.toString()
        val instance = getCurrentDateTime()
        val chbStart = onWork



        if (worker.isEmpty() || work.isEmpty()) {
            Toast.makeText(requireActivity() as MainActivity,"Παρακαλω Επιλεξτε Εργασια και Εργαζομενο", Toast.LENGTH_SHORT).show()
        }else {




            val ent = Entry(name = worker, work = work, timestamp = instance, problems = problems, chbStart = chbStart )
            val status = sqLiteHelper.addEntry(ent)

            if (status > -1) {
                Toast.makeText(requireActivity() as MainActivity, "Η καταχωρηση ηταν επιτυχης", Toast.LENGTH_SHORT).show()
                binding.EditProblemsId.setText("")
                
            }else{
                Toast.makeText(requireActivity() as MainActivity, "Η καταχωρηση σας Δεν ηταν επιτυχης", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /*fun isChecked(view: View) {
        view as CheckBox
        val checked :Boolean = view.isChecked

        when(view.id) {
            R.id.chbStart -> {
                if (checked) binding.spnWorkList.setBackgroundColor(Color.GRAY)

                    this.binding.chbFinish.isChecked = false
            }
            R.id.chbFinish -> {
                if (checked) binding.spnWorkList.setBackgroundColor(Color.DKGRAY)
                    this.binding.chbStart.isChecked = false
            }
        }

    }*/

    private fun getEntries() {
        val entrylist = sqLiteHelper.getEntry()
        adapter.addItems(entrylist)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        return
    }}





