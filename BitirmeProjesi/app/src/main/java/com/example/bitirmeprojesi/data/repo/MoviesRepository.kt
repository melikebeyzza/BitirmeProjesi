package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.datasource.MoviesDataSource
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movie_Cart_state
import com.example.bitirmeprojesi.data.entity.Movies

class MoviesRepository(var mds: MoviesDataSource) {

    suspend fun moviesUp(): List<Movies> = mds.moviesUp()



    suspend fun cartMovie( request :Movie_Cart_state ) = mds.cartMovie(request)

}