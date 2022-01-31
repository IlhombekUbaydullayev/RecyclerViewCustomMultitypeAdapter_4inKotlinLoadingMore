package com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.R
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.adapter.CustomAdapter
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.listener.OnBottomReachedListener
import com.example.recyclerviewcustommultitypeadapter_4inkotlinloadingmore.activity.model.Member

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        val members = prepareMemberList()
        refreshAdapter(members)
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = GridLayoutManager(this,1)
    }

    private fun refreshAdapter(members : List<Member>) {
        val adapter = CustomAdapter(members,object : OnBottomReachedListener {
            override fun onBottomReached(position: Int) {
                Log.d("@@@","@@@position"+position)
            }
        })
        recyclerView!!.adapter = adapter
    }

    private fun prepareMemberList(): List<Member> {
        val members = ArrayList<Member>()
        members.add(Member("","",false))
        for (i in 0..29) {
            if (i == 0 || i == 5 || i == 16 || i == 25) {
                members.add(Member("Ilhombek" + i, "Ubaydullayev" + i,false))
            }
            else {
                members.add(Member("Ilhombek" + i,"Ubaydullahyev" + i,true))
            }
        }
        members.add(Member("","",false))
        return members
    }
}