package com.ymars.poj.base.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustFragmentPagerAdapter : FragmentStateAdapter {
    private var fragments: SparseArray<Fragment>
    constructor(
        manager: FragmentManager,
        lifecycle: Lifecycle,
        fragments: SparseArray<Fragment>
    ) : super(
        manager,
        lifecycle
    ) {
        this.fragments = fragments
    }


    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}