/*package com.greymatter.politems.fragment

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.android.material.snackbar.Snackbar
import com.greymatter.politems.R


class ConsumerShopFragment : Fragment()
{

    /* access modifiers changed from: private */
    var parent_view: View? = null
    private var recyclerView: RecyclerView? = null
    private var mAdapter: AdapterGridBasic? = null

    companion object
    {
        fun newInstance() =
            ConsumerShopFragment()
    }

    //private lateinit var viewModel: ConsumerShopViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View?
    {
        parent_view = container!!.findViewById(android.R.id.content)
        initComponent(container)
        return inflater.inflate(R.layout.fragment_consumer_shop, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

//        ViewModelProviders.of(this).get(ConsumerShopViewModel::class.java)
//            .getUsers().observe(viewLifecycleOwner,  Observer<List<Product>> { products ->
//
//        })
        // TODO: Use the ViewModel
    }

    private fun initComponent(container: ViewGroup) {
        recyclerView = container.findViewById<View>(R.id.recyclerView_products) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(this, 3)
        recyclerView!!.addItemDecoration(
            SpacingItemDecoration(
                3,
                Tools.dpToPx(this, 2),
                true
            )
        )
        recyclerView!!.setHasFixedSize(true)
        val natureImages: MutableList<Int> = DataGenerator.getNatureImages(this)
        natureImages.addAll(DataGenerator.getNatureImages(this))
        natureImages.addAll(DataGenerator.getNatureImages(this))
        natureImages.addAll(DataGenerator.getNatureImages(this))
        mAdapter = AdapterGridBasic(this, natureImages)
        recyclerView!!.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : AdapterView.OnItemClickListener
        {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long )
            {
                Snackbar.make(parent_view!!, "Item $id clicked", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}

class GridBasic : AppCompatActivity() {




    /* access modifiers changed from: protected */
    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout. as Int)


    }

}
*/