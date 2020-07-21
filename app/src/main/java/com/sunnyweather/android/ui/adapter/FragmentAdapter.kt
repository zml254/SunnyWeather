package com.sunnyweather.android.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter: FragmentStateAdapter {

    private lateinit var fragments: List<Fragment>

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    constructor(fragmentActivity: FragmentActivity, fragments: List<Fragment>) : this(
        fragmentActivity
    ){
        this.fragments = fragments
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}