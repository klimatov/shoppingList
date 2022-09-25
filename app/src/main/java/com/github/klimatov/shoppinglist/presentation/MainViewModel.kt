package com.github.klimatov.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.klimatov.shoppinglist.data.ShopListRepositoryImpl
import com.github.klimatov.shoppinglist.domain.DeleteShopItemUseCase
import com.github.klimatov.shoppinglist.domain.EditShopItemUseCase
import com.github.klimatov.shoppinglist.domain.GetShopListUseCase
import com.github.klimatov.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel(){

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList() // слушаемая из активити LIveData

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}