package com.aditya.stockmarketapp.presentation.company_info

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aditya.stockmarketapp.R
import com.aditya.stockmarketapp.ui.theme.DarkBlue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Destination
fun CompanyInfoScreen(
    symbol: String,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val companyInfoState = viewModel.companyInfoState
    val stockInfosState = viewModel.stockInfosState

    if (companyInfoState.company != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Change background color to white
                .padding(16.dp)
        ) {
            item {
                companyInfoState.company?.let { company ->
                    company.name?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            fontSize = 44.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(),
                            style = androidx.compose.ui.text.TextStyle(
                                lineHeight = 1.2.em
                                // Adjust line height as needed
                            ),
                            color = Color.Black // Set text color to black
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF00004d)) // Set the background color here
                    ) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Market Summary", color = Color.White)

                            Spacer(modifier = Modifier.width(5.dp))
                            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.reload))
                            var isPlaying by remember { mutableStateOf(false) }

                            LottieAnimation(
                                composition = composition,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        isPlaying = !isPlaying
                                        viewModel.refreshIntradayInfo()
                                    },
                                progress = animateLottieCompositionAsState(
                                    composition = composition, isPlaying = isPlaying
                                ).value,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        if (stockInfosState.stockInfos.isNotEmpty()) {
                            val firstIntradayInfo = stockInfosState.stockInfos.firstOrNull()
                            val dayOfWeek = firstIntradayInfo?.date?.dayOfWeek?.name
                            val dayOfMonth = firstIntradayInfo?.date?.dayOfMonth
                            val month = firstIntradayInfo?.date?.month
                            val year = firstIntradayInfo?.date?.year

                            StockChart(
                                infos = stockInfosState.stockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            Text(
                                text = "$dayOfWeek, $dayOfMonth $month $year",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White // Set text color to black
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (stockInfosState.error != null) {
                                Text(
                                    text = stockInfosState.error,
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center
                                )
                            } else if (stockInfosState.isLoading) {
                                CircularProgressIndicator()
                            } else if (stockInfosState.stockInfos.isEmpty()) {
                                Text(
                                    text = "Market Summary not available",
                                    color = Color.White, // Set text color to black
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(18.dp))
                    company.symbol?.let {
                        Text(
                            text = "Company symbol: $it",
                            fontStyle = FontStyle.Italic,
                            fontSize = 28.sp,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black // Set text color to black
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(18.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, Color.Black) // 2dp width border
                            .padding(8.dp) // Optional padding inside the border
                    ) {
                        Column {
                            Text(
                                text = "Industry: ${company.industry}",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black // Set text color to black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Country: ${company.country}",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black // Set text color to black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    company.description?.let {
                        Text(
                            text = it,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black // Set text color to black
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Address: ${company.address}",
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Set text color to black
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (companyInfoState.isLoading) {
            CircularProgressIndicator()
        } else if (companyInfoState.error != null) {
            Text(
                text = companyInfoState.error,
                color = Color.Black, // Set text color to black
                textAlign = TextAlign.Center
            )
        } else if (companyInfoState.company == null) {
            Text(
                text = "Data not available screen",
                color = Color.Black, // Set text color to black
                textAlign = TextAlign.Center
            )
        }
    }
}
