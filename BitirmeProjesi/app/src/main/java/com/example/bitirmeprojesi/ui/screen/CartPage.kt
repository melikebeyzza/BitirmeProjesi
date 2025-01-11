package com.example.bitirmeprojesi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.ui.viewmodel.HomeViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage ( onBackClick : () -> Unit,
               cartId: Int?, image: String?, name: String?, price: Float?, desc : String?
) {
    Scaffold(
        topBar = {
            TopAppBar(colors= TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor =Color.Gray),
                title = {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.clickable { onBackClick() }
                        )
                        Text(text = "CART")
                    }

                },

                )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
                ) {


                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = name.toString(),
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                color = Color.Black
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            val url = "http://kasimadalan.pe.hu/movies/images/$image"
                            com.skydoves.landscapist.glide.GlideImage(
                                imageModel = url,
                                modifier = Modifier.size(100.dp, 100.dp),
                                contentDescription = null
                            )

                            Text(
                                text = "PRICE=$price" ,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    color = Color.Black,
                                    letterSpacing = 0.2.sp,
                                ),

                                modifier = Modifier
                            )

                        }
                    }

                }
            }
            Text(
                    text = "Total price=$price")
                Button(
                    onClick = {},  shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray))
                {

                    Text(text = "Applied")

                }



        }

    }
}


