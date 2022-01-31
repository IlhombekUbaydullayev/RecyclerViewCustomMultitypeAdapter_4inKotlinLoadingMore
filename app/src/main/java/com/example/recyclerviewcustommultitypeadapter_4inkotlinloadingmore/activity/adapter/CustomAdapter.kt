package com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.R
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.listener.OnBottomReachedListener
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.model.Member

class CustomAdapter(val members: List<Member>,val listener : OnBottomReachedListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_ITEM_HEADER = 0
    private val TYPE_ITEM_YES_VIEW = 1
    private val TYPE_ITEM_NOT_VIEW = 2
    private val TYPE_ITEM_FOOTER = 3
    override fun getItemCount(): Int {
        return members.size
    }

    override fun getItemViewType(position: Int): Int {

        if (isHeader(position)) return TYPE_ITEM_HEADER
        if (isFooter(position)) return TYPE_ITEM_FOOTER
        return if (members.get(position).available) {
            TYPE_ITEM_YES_VIEW
        }
        else TYPE_ITEM_NOT_VIEW
    }

    private fun isFooter(position: Int): Boolean {
        return position == members.size -1
    }

    private fun isHeader(position: Int): Boolean {
        return position == 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_ITEM_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_layout_header,parent,false)
            return CustomViewHeaderHolder(view)
        }else if (viewType == TYPE_ITEM_YES_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_layout_yes,parent,false)
            return CustomViewYesHolder(view)
        }else if (viewType == TYPE_ITEM_NOT_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_layout_not,parent,false)
            return CustomViewNotHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_layout_footer,parent,false)
        return CustomViewFooterHolder(view)
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == members.size-1) {
           listener.onBottomReached(position)
        }
        val member = members.get(position)

        if (holder is CustomViewYesHolder) {
            holder.first_name.setText(member.first_name.toString())
            holder.last_name.setText(member.last_name.toString())
        }

        if (holder is CustomViewNotHolder) {
            holder.first_name.setText("this firstname is not availabler")
            holder.last_name.setText("this lastname is not available")
        }

    }

    class CustomViewHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class CustomViewYesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val first_name = itemView.findViewById<TextView>(R.id.first_name)
        val last_name = itemView.findViewById<TextView>(R.id.last_name)
    }

    class CustomViewNotHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val first_name = itemView.findViewById<TextView>(R.id.first_name)
        val last_name = itemView.findViewById<TextView>(R.id.last_name)
    }

    class CustomViewFooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}