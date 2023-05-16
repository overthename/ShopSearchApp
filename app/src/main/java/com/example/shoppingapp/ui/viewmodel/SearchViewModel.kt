package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.repository.ShopSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val shopSearchRepository: ShopSearchRepository
) : ViewModel(){


//    Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchShops(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = shopSearchRepository.searchShops(query,20,1)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }


}