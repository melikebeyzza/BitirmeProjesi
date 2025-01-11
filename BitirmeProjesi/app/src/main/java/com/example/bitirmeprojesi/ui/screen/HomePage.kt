package com.example.bitirmeprojesi.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.ui.viewmodel.HomeViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController, homeViewModel: HomeViewModel,
    onsepetClick: (Movie_Cart) -> Unit
) {
    val movieList = homeViewModel.moviesList.observeAsState(listOf())
    val socope = rememberCoroutineScope()
    var search = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val sepet = savedStateHandle?.get<Movie_Cart>("sepet")
    var sepets by rememberSaveable { mutableStateOf(sepet) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "WELCOME") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.Gray
            )
        )
    },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .padding(paddingValues),
            columns = GridCells.Fixed(count = 2)
        ) {
            items(count = movieList.value.count(), itemContent = {
                val movie = movieList.value[it]
                Card(modifier = Modifier.padding(all = 3.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF000000))
                            .clickable {
                                // Movie tıklanınca, ilgili detay sayfasına geçiş yap
                                val movieJson = Gson().toJson(movie)
                                navController.navigate("detailpage/${movieJson}")
                            }
                    ) {
                        val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                        com.skydoves.landscapist.glide.GlideImage(
                            imageModel = url,
                            modifier = Modifier.size(200.dp, 300.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "${movie.price}$",
                                fontSize = 24.sp,
                                color = Color(0xFF625b71)
                            )
                            Button(
                                onClick = {
                                    // film sepete ekle
                                    val cartItem = Movie_Cart(
                                        cartId = movie.id,
                                        name = movie.name,
                                        price = movie.price,
                                        image = movie.image,
                                        description = movie.description
                                    )
                                    // sepet bilgilerini gönder
                                    onsepetClick(cartItem)
                                    // ekranlar arası geçiş
                                    navController.navigate("cartPage/${cartItem.cartId}/${cartItem.image}/${cartItem.name}/${cartItem.price}/${cartItem.description}")
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(Color.LightGray)
                            ) {
                                Text(text = "Add to cart")
                            }
                        }
                    }
                }
            })
        }
    }
}



