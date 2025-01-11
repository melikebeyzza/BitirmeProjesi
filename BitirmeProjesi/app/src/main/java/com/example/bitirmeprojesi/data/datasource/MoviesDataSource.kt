package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movie_Cart_state
import com.example.bitirmeprojesi.data.entity.Movies
import com.example.bitirmeprojesi.retrofit.MoviesDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataSource(var mdao: MoviesDAO) {
    suspend fun moviesUp(): List<Movies> = withContext(Dispatchers.IO) {
        return@withContext mdao.moviesUp().movies
    }
    suspend fun delete(cart_id: Int) {
        mdao.delete(cart_id)
    }

    suspend fun  cartMovie (request : Movie_Cart_state){
        mdao.cartMovie(request)
    }
}