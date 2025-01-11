package com.example.bitirmeprojesi.retrofit


import com.example.bitirmeprojesi.data.entity.CRUDAnswer
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movie_Cart_state
import com.example.bitirmeprojesi.data.entity.MoviesAnswer
import okhttp3.Request
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MoviesDAO {

    //http://kasimadalan.pe.hu/movies/getAllMovies.php
    //http://kasimadalan.pe.hu/  -> BASE URL
    //movies/getAllMovies.php -> API URL

    //liste ekranı
    @GET("movies/getAllMovies.php")
    suspend fun moviesUp(): MoviesAnswer

    // sepetti veri görme
    @GET("cart")
    suspend fun getCardItems(@Query("username") userName: String="melikebeyza"):List<Movie_Cart>


    @POST("movies/insertMovie.php")
    suspend fun  cartMovie(request : Movie_Cart_state ): CRUDAnswer

    // liste ekranında sepete gönderme
    @POST("movies/getMovieCart.php")
    suspend fun getCartMovie(@Body cartId:Int):CRUDAnswer

    // sepet silme
    @POST("movies/deleteMovie.php")
    suspend fun delete(@Field("cartId")cartId:Int):CRUDAnswer

}