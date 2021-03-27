package com.ben.wordy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlidePageAdapter(manager: FragmentActivity) :
    FragmentStateAdapter(manager) {

    private val tabs: MutableList<Pair<Fragment, String>> = mutableListOf()

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment = tabs[position].first

    fun addTab(fragment: Fragment, title: String) {
        tabs.add(Pair(fragment, title))
    }

    fun getTitle(position: Int) : String = tabs[position].second
}