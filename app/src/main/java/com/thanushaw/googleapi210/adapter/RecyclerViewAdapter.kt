package com.thanushaw.googleapi210.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanushaw.googleapi210.R
import com.thanushaw.googleapi210.api.University
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RecyclerViewAdapter(private val mList: List<University>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // change to different subjects from rx.subjects to get different behavior
    // BehaviorSubject for example allows to receive last event on subscribe
    // PublishSubject sends events only after subscribing on the other hand which is desirable for clicks
    private val clickSubject = PublishSubject.create<String>()
    val clickEvent : Observable<String> = clickSubject
    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView_name: TextView = itemView.findViewById(R.id.textView_name)
        val textView_id: TextView = itemView.findViewById(R.id.textView_id)
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(textView_id.text as String)
            }
        }
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView_name.text = item.name
        holder.textView_id.text = item.id.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }


}