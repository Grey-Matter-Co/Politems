package com.greymatter.politems.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.greymatter.politems.R
import com.greymatter.politems.utils.ViewAnimation

class SellerMainActivity : AppCompatActivity()
{
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fab: FloatingActionButton
    private var viewIsAtHome = true
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_main)

//          setup of frameContainer
        frameLayout = findViewById(R.id.fragment_container)

//          setup of bottomNav
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//          setup of FAB
        fab = findViewById(R.id.fabSectionMain)
        fab.setOnLongClickListener {
            val toast :Toast = Toast(this)
            val inflater : LayoutInflater = layoutInflater
            val mView: View = inflater.inflate(
                R.layout.toast_basic, findViewById(
                    R.id.toast_container
                ))
            mView.findViewById<TextView>(R.id.toast_text).text = it.contentDescription
            toast.duration = Toast.LENGTH_SHORT
            toast.view = mView
            toast.show()
            return@setOnLongClickListener true
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        frameLayout.let { ViewAnimation.fadeOutIn(it) }
        when (item.itemId)
        {
            R.id.navigation_inventory ->
            {
                viewIsAtHome = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_orders ->
            {
                viewIsAtHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings ->
            {
                viewIsAtHome = false
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()

    override fun onBackPressed()
    {
        if (!viewIsAtHome) //if the current view is not the News fragment
            bottomNavigation.selectedItemId =
                R.id.home //display the News fragment
        else
            moveTaskToBack(true) //If view is in News fragment, exit application
    }
}