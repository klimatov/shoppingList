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

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}