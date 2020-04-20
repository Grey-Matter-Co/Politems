package com.greymatter.politems

//import com.greymatter.politems.utils.Tools
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.greymatter.politems.utils.ViewAnimation


//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_consumer_main)
//    }
//}


class ConsumerMainActivity : AppCompatActivity() {
    private var actionBar: ActionBar? = null
    private var nestedScrollView: NestedScrollView? = null

    /* access modifiers changed from: protected */
    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_consumer_main)
        initToolbar()
        initComponent()
    }

    private fun initToolbar() {
//        val toolbar =
//            findViewById<View>(R.id.toolbar) as Toolbar
//        toolbar.setNavigationIcon(R.drawable.ic_menu as Int)
//        toolbar.navigationIcon
//            .setColorFilter(resources.getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP)
//        setSupportActionBar(toolbar)
        actionBar = supportActionBar
        actionBar!!.title = "Shopping"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        Tools.setSystemBarColor(this, R.color.grey_5)
//        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        nestedScrollView =
            findViewById(R.id.nested_scroll_view)
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_1) as ImageView,
//            R.drawable.image_8 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_2) as ImageView,
//            R.drawable.image_9 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_3) as ImageView,
//            R.drawable.image_15 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_4) as ImageView,
//            R.drawable.image_14 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_5) as ImageView,
//            R.drawable.image_12 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_6) as ImageView,
//            R.drawable.image_2 as Int
//        )
//        Tools.displayImageOriginal(
//            this as Context,
//            findViewById<View>(R.id.image_7) as ImageView,
//            R.drawable.image_5 as Int
//        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_search, menu)
//        Tools.changeMenuIconColor(menu, resources.getColor(R.color.grey_60))
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean
    {
        if (menuItem.itemId == 16908332)
            finish()
        else
            Toast.makeText(applicationContext, menuItem.title, Toast.LENGTH_LONG).show()

        return super.onOptionsItemSelected(menuItem)
    }

    fun clickAction(view: View?)
    {
        nestedScrollView?.let { ViewAnimation.fadeOutIn(it) }
    }
}

