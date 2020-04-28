package com.greymatter.politems.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.greymatter.politems.R
import com.greymatter.politems.utils.ViewAnimation
class ConsumerMainActivity : AppCompatActivity()
{
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fab: FloatingActionButton
    private var viewIsAtHome = true
    private lateinit var frameLayout: FrameLayout

    /* access modifiers changed from: protected */
    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_consumer_main)

//          setup of frameContainer
        frameLayout = findViewById(R.id.fragment_container)
        addFragment(Fragment(R.layout.fragment_consumer_shop))

//          setup of bottomNav
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            return@OnNavigationItemSelectedListener itemSelected(item.itemId)
        })

//          setup of FAB
        fab = findViewById(R.id.fabSectionMain)
        fab.setOnLongClickListener {
            val toast = Toast(this)
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

    private fun itemSelected(itemId: Int): Boolean
    {
        frameLayout.let { ViewAnimation.fadeOutIn(it) }
        when (itemId)
        {
            R.id.navigation_shop, R.id.fabSectionMain ->
            {
                viewIsAtHome = true
                addFragment(Fragment(R.layout.fragment_consumer_shop))
                return true
            }
            R.id.navigation_orders ->
            {
                viewIsAtHome = false
                //addFragment(Fragment(R.layout.fragment_consumer_orders))
                return true
            }
            R.id.navigation_settings ->
            {
                viewIsAtHome = false
                //addFragment(Fragment(R.layout.fragment_consumer_setting))
                return true
            }
        }
        return false
    }

    fun fabSelected(view: View){ itemSelected(view.id)}

    private fun addFragment(fragment: Fragment)
    {
        frameLayout.let { ViewAnimation.fadeOutIn(it) }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed()
    {
        if (!viewIsAtHome) //if the current view is not the News fragment
            bottomNavigation.selectedItemId =
                R.id.navigation_shop //display the News fragment
        else
            moveTaskToBack(true) //If view is in News fragment, exit application
    }
}

