package com.example.spinnerrecycler

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spinnerrecycler.db.Entry

class EntryAdapter : RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    private var entryList: ArrayList<Entry> = ArrayList()
    private var onClickItem: ((Entry) -> Unit)? = null
    private var onClickDeleteItem: ((Entry) -> Unit)? = null

    fun addItems(items: ArrayList<Entry>) {
        this.entryList = items
        notifyDataSetChanged()
    }
    fun setOnClickItem(callback: (Entry) -> Unit) {
        this.onClickItem = callback
    }

    fun onClickDeleteItem(callback: (Entry) -> Unit) {
        this.onClickDeleteItem = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ent = entryList[position]
        holder.bindView(ent)
        holder.itemView.setOnClickListener { onClickItem?.invoke(ent) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(ent) }
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<Button>(R.id.IdTv)
        private var name = view.findViewById<TextView>(R.id.NameTV)
        private var work = view.findViewById<TextView>(R.id.WorkTv)
        private var problems = view.findViewById<TextView>(R.id.ProblemTv)
        private var timestamp = view.findViewById<TextView>(R.id.TimestampTV)
        var checkBox = view.findViewById<TextView>(R.id.checkBoxTV)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(entry: Entry){
            id.text = entry.id.toString()
            name.text = entry.name
            work.text = entry.work
            problems.text = entry.problems
            timestamp.text = entry.timestamp
            // Χρειαζεται αλλαγη η μεταβλητη
            checkBox.text = entry.chbStart.toString()



        }

    }


}