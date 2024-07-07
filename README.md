# Groww Edge - Stock App 📈📲

## Overview
Groww Edge is an Android application that provides users with comprehensive access to stock market information using the Alpha Vantage API. The app allows users to view a list of listed stocks, along with detailed company information and intraday information for each stock.


<p align="center">
  <img src="https://github.com/KA2501/GrowwEdge/assets/104622097/4ce506dd-2797-40da-bbf1-c077628c2f2f" alt="Image 1" width="200"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/KA2501/GrowwEdge/assets/104622097/0e01f80a-fbd1-4cdc-a1a7-87ac568722ea" alt="Image 2" width="200"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/KA2501/GrowwEdge/assets/104622097/934b5ba1-8503-4c07-9de6-3a2791d65a7f" alt="Image 3" width="200"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/KA2501/GrowwEdge/assets/104622097/c3dd2fe2-9889-4c16-bc76-026efc97e4f8" alt="Image 4" width="200"/>
</p>


## Features 🌟

### Listed Stocks
- Comprehensive Stock List: Fetches a large list of listed stocks from the Alpha Vantage API.
- Fast Access: Caches the list of all listed stocks for quicker searching.

### Explore Screens 📊
Groww Edge includes two main explore screens:
- Top Gainers Section 🚀
- Top Losers Section 📉

Each section is displayed in tabs and contains a grid of cards showing information about stocks/ETFs. The displayed information includes:
- `company.exchange`
- `company.name`
- `company.symbol`

### Product Screen
Upon clicking any stock, users are taken to a product screen showing:
- `company.name`
- `company.symbol`
- `company.industry`
- `company.country`
- `company.address`
- "Market Summary" displayed as a line graph showing stock prices 📈

### Pull-to-Refresh
- Users can refresh the listed stock list by swiping down, ensuring they receive the latest data.

### Stock Details 🧾
- Detailed Information: Clicking on any stock allows users to view detailed company information and intraday information of that stock for the previous day.
- Intraday Information Note: If users view intraday information on Monday, the app will display Saturday's intraday information, since the market is closed on Sundays.
- Manual Refresh: Users can manually refresh the intraday information for the selected stock by clicking on the refresh icon.

### Local Caching 🗂️
- Faster Searching: Caches the list of all listed stocks for quicker access.
- Offline Access: Viewed stock details, such as the intraday information and company information, are saved into the local cache, enabling offline access for subsequent views.

## Screens 📺

### Explore Screen
The Explore Screen includes two grids within lazy columns that display basic information about each company. Each item in the grid includes:
- `company.exchange`
- `company.name`
- `company.symbol`

### Product Screen
When a stock is clicked, the Product Screen displays:
- `company.name`
- `company.symbol`
- `company.industry`
- `company.country`
- `company.address`
- "Market Summary" as a line graph of prices 📉

## Installation 🛠️
To install Groww Edge, follow these steps:
1. Clone the repository.
    ```bash
    git clone https://github.com/KA2501/GrowwEdge
    ```
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Usage 📲
1. Open the app to view the Explore Screen with the Top Gainers and Losers tabs.
2. Pull down to refresh the list of listed stocks.
3. Click on any stock to view detailed company information and intraday information for the previous day.
4. Click the refresh icon on the Product Screen to update the intraday information.

## Conclusion ✨
Groww Edge is a powerful tool for anyone looking to stay informed about the stock market. With features like detailed stock information, pull-to-refresh functionality, and local caching for offline access, Groww Edge ensures users have the latest market data at their fingertips.

🔗 [Download the APK](https://drive.google.com/drive/folders/10C6gMYgzRGPtmbXHZCr2NOzrAK2mO3fX?usp=sharing)  
🎥 [Watch the Demo](https://youtu.be/s_g90owOoe8)


Download Groww Edge today and take control of your stock market insights! 🚀📊
