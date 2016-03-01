# spring-boot-wechat-barcode
Backend codes for processing barcodes based on spring boot.

## API
Client: `POST` request to `/product`
Server: load the data into database

Client: `GET` request to `/product/{barcode}`
Server: return the product information with `{barcode}`

Client: `GET` request to `/brand/{brandId}`
Server return the brand information with `{brandId}`

## ngrok
    
    ./ngrok http 8080
