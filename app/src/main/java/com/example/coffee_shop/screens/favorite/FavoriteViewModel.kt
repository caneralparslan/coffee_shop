package com.example.coffee_shop.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.repository.CoffeeShopDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: CoffeeShopDbRepository): ViewModel(){

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        // Launching coroutine with IO dispatcher for database operations
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getFavorites()
                    .catch { _favList.value = emptyList() }
                    .collect { listOfFavorites ->
                        _favList.value = listOfFavorites
                    }
            } catch (e: Exception) {
                _favList.value = emptyList()
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

    fun removeAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }
}