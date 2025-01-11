package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movie_Cart_state
import com.example.bitirmeprojesi.data.entity.Movies
import com.example.bitirmeprojesi.data.repo.MoviesRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor( var mrepo : MoviesRepository) : ViewModel() {
    var moviesList = MutableLiveData<List<Movies>>()


    init {
        moviesUp()
    }

    fun moviesUp (){
        CoroutineScope(Dispatchers.Main).launch {
           moviesList.value= mrepo.moviesUp()
        }
    }

    fun cartMoview(orderAmount : Int,userName: String){
        viewModelScope.launch {
          val request = Movie_Cart_state(
              orderAmount = orderAmount,
              userName = userName
          )
           mrepo.cartMovie(request)
        }
    }


}