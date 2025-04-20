package com.example.coffee_shop.screens.favorite

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.models.ItemCategory
import com.example.coffee_shop.repository.CoffeeShopDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: CoffeeShopDbRepository): ViewModel(){

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites()
                .collect{
                        listOfFavorites ->
                    _favList.value = listOfFavorites
                }
        }
    }

    fun addFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.addFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.updateFavorite(favorite)
    }

    fun removeFavorite(id: String) = viewModelScope.launch {
        repository.removeFavorite(id)
    }
}