package com.greymatter.politems.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greymatter.politems.model.Product

class ConsumerShopViewModel : ViewModel()
{
    private val products: MutableLiveData<List<Product>> = TODO()

    fun getUsers(): LiveData<List<Product>>
    {
        return products
    }

    /**
     *  @description: there load products data from any server
     */
    private fun loadUsers()
    {
        // Do an asynchronous operation to fetch users.
    }
}
