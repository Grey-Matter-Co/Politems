package com.greymatter.politems.model

import android.media.Image

class Product(var name: String,
              var price: Float,
              var category: String,
              var images: Array<Image>,
              var description: String)
{}