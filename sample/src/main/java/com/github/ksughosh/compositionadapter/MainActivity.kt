package com.github.ksughosh.compositionadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.ksughosh.adapter.ViewDelegate
import com.github.ksughosh.adapter.DataAdapter
import com.github.ksughosh.adapter.ListItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val delegateOne = ViewDelegate({ DataOneView(it?.context) }, { (it as? DataOne)?.isShown == false })
        val delegateTwo = ViewDelegate({ DataTwoView(it?.context) }, { it is DataTwo })
        val delegateThree = ViewDelegate({ DataOneView2(it?.context) }, { (it as? DataOne)?.isShown == true})

        listView?.adapter = DataAdapter(generateDummyList()).addDelegate(delegateOne, delegateTwo, delegateThree)
        listView?.layoutManager = LinearLayoutManager(this)
    }

    private fun generateDummyList(): List<ListItem> {
        val list = mutableListOf<ListItem>()
        list.add(DataOne(true, true))
        list.add(DataTwo(1, "name"))
        list.add(DataOne(false))
        list.add(DataTwo(2, "name"))
        list.add(DataOne(true, false))
        list.add(DataTwo(3, "name"))
        list.add(DataOne(false))
        list.add(DataTwo(4, "name"))
        return list
    }
}
