# CoinbaseOrderBook

So far this program pulls the top 50 ETH-USD bids and asks on Coinbase Pro from their public API, parses the JSON, and prints them out

Next potential steps:
- abstract out ETH-USD so this can be used for any crypto pair (see: https://help.coinbase.com/en/pro/trading-and-funding/cryptocurrency-trading-pairs/locations-and-trading-pairs)
- abstract out the quantity of data -> Coinbase Pro has three levels (see: https://docs.pro.coinbase.com/#get-product-order-book)
- import into csv file for ease of use 
- data visualization & calculation (calculate spread, visualize bid/asks, etc.)
- automate such that this data is pulled at a regular interval --> might need to shift from a HTTP GET to websocket 
