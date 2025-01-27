package com.example.ihatefridays.model

import com.google.gson.annotations.SerializedName

data class MakeUp(
    val api_featured_image: String,
    val brand: String,
    val category: String,
    val created_at: String,
    val currency: Any,
    val description: String,
    val id: Int,
    @SerializedName("image_link")
        val image_link: String,
    val name: String,
    val price: String,
    val price_sign: Any,
    val product_api_url: String,
    val product_colors: List<ProductColor>,
    val product_link: String,
    val product_type: String,
    val rating: Double,
    val tag_list: List<Any>,
    val updated_at: String,
    val website_link: String
)