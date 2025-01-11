package com.example.bitirmeprojesi.data.entity

data class Movie_Cart(
    var cartId: Int?=null,
    var name: String?=null,
    var image: String?=null,
    var price: Int?=null,
    var category: String?=null,
    var rating: Double?=null,
    var year: Int?=null,
    var directior: String?=null,
    var description: String?=null,
    var orderAmount: Int?=null,
    var userName: String?=null,
)


data class Movie_Cart_state(
    var orderAmount: Int?=null,
    var userName: String?=null
)