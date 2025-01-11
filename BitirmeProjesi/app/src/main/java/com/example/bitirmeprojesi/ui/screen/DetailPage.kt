package com.example.bitirmeprojesi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movies


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    getMovie: Movies,
    onBackClick: () -> Unit,
    navController: NavController,
    onCartClick: (Movie_Cart) -> Unit,
    onListClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.Gray
                ),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onBackClick()
                            }
                        )
                        Text(text = getMovie.name)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFFFFFF)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Film Resmi
            val url = "http://kasimadalan.pe.hu/movies/images/${getMovie.image}"
            com.skydoves.landscapist.glide.GlideImage(
                imageModel = url,
                modifier = Modifier.size(200.dp, 300.dp)
            )

            // "Easy returns" yazısı ve Add To List butonu
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Easy returns within 30 days",
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF000000)
                )

                // Add to List butonu
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onListClick,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray)
                    ) {
                        Text(text = "Add To List", color = Color.Black)
                    }
                }

                // Adet seçme
                val number = remember { mutableStateOf(1) } // sayac

                // Adet artırma ve azaltma
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Azaltma Butonu
                    Button(
                        onClick = {
                            if (number.value > 1) {
                                number.value -= 1
                            }
                        },
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray)
                    ) {
                        Text(text = "-", fontSize = 24.sp, color = Color(0xFF000000))
                    }

                    // Adet Değeri
                    Text(
                        text = "${number.value}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // Arttırma Butonu
                    Button(
                        onClick = {
                            number.value += 1
                        },
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray)
                    ) {
                        Text(text = "+", fontSize = 50.sp, color = Color(0xFF000000))
                    }
                }

                // Toplam fiyat hesaplama
                val final = (getMovie.price) * number.value

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "TOTAL PRICE : $${final}", fontSize = 20.sp, color = Color.Black)

                    // Sepete ekleme butonu
                    Button(
                        onClick = {
                            // sepet item
                            val cartItem = Movie_Cart(
                                cartId = getMovie.id,
                                name = getMovie.name,
                                price = getMovie.price,
                                image = getMovie.image,
                                description = getMovie.description
                            )
                            // Sepete ekle
                            onCartClick(cartItem)

                            // cart sayfasına geçiş
                            navController.navigate(
                                "cartPage/${cartItem.cartId}/${cartItem.image}/${cartItem.name}/${cartItem.price}/${cartItem.description}"
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray)
                    ) {
                        Text(text = "Add to Cart", color = Color.Black)
                    }
                }
            }
        }
    }
}


