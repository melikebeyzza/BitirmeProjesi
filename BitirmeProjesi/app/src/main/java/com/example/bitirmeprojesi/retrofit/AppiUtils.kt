package com.example.bitirmeprojesi.retrofit

class AppiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getMoviesDao(): MoviesDAO {

            return RetrofitClient.getClient(BASE_URL).create(MoviesDAO::class.java)
        }
    }
}