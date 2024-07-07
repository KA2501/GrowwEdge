package com.aditya.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aditya.stockmarketapp.presentation.destinations.CompanyInfoScreenDestination
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun CompanyListingScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListingViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Setting the background color to white
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(
                        CompanyListingsEvent.OnSearchQueryChange(it)
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "Search...")
                },
                maxLines = 1,
                singleLine = true
            )

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(CompanyListingsEvent.Refresh)
                }
            ) {
                // Use LazyColumn for displaying items
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Create two columns manually
                    val halfSize = (state.companies.size + 1) / 2 // Calculate half size for two columns
                    items(halfSize) { index ->
                        val index1 = index * 2
                        val index2 = index * 2 + 1
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (index1 < state.companies.size) {
                                CompanyItem(
                                    company = state.companies[index1],
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .height(IntrinsicSize.Max) // Ensuring equal height for all items
                                        .clickable {
                                            navigator.navigate(
                                                CompanyInfoScreenDestination(state.companies[index1].symbol)
                                            )
                                        }
                                )
                            }
                            if (index2 < state.companies.size) {
                                Spacer(modifier = Modifier.width(8.dp))
                                CompanyItem(
                                    company = state.companies[index2],
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .height(IntrinsicSize.Max) // Ensuring equal height for all items
                                        .clickable {
                                            navigator.navigate(
                                                CompanyInfoScreenDestination(state.companies[index2].symbol)
                                            )
                                        }
                                )
                            }
                        }
                        if (index < halfSize - 1) {
                            Divider()
                        }
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
