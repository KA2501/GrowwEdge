package com.aditya.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.aditya.stockmarketapp.domain.model.CompanyListing

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White) // Setting the background color to white
            .fillMaxWidth()          // Making the Box fill the entire width of its parent
            .padding(8.dp)           // Adding padding around the Box
            .border(                 // Adding border with rounded corners
                BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center // Aligning the contents vertically centered
        ) {
            // Text for the company name
            Text(
                text = company.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = Color.Black, // Changing text color to black
                overflow = TextOverflow.Ellipsis,
                maxLines = 2, // Limiting to 2 lines for the company name
                style = androidx.compose.ui.text.TextStyle(
                    lineHeight = 1.2.em // Adjusting line height for readability
                )
            )

            // Spacer to add some vertical space between the name and the symbol
            Spacer(modifier = Modifier.height(4.dp))

            // Text for the company symbol
            Text(
                text = "(${company.symbol})",
                fontStyle = FontStyle.Italic,
                color = Color.Black, // Changing text color to black
            )

            // Spacer to add some vertical space between the symbol and the exchange
            Spacer(modifier = Modifier.height(4.dp))

            // Text for the exchange
            Text(
                text = company.exchange,
                fontWeight = FontWeight.Light,
                color = Color.Black, // Changing text color to black
            )
        }
    }
}
