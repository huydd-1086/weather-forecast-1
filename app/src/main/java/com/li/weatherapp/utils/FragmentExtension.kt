package com.li.weatherapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.removeFragment(fragment: Fragment) {
    popBackStack()
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        .remove(fragment)
        .commit()
}

fun FragmentManager.replaceFragment(
    layout: Int,
    fragment: Fragment,
    tag: String = fragment.javaClass.name
) {
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(layout, fragment, tag)
        .addToBackStack(null)
        .commit()
}

