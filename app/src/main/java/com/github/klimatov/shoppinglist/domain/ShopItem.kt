package com.github.klimatov.shoppinglist.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)