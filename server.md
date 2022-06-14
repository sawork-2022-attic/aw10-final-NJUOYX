discovery:
    port = 8000

gateway:
    port = 8001

product:
    port = 8002
    api:
        get: /product
        get: /product/{asin}

cart:
    port = 8003
    api:
        get:  /cart
        post: /cart/add (requestBody= item)
        get:  /cart/total
        post: /cart/checkout (requestBody=null)

database:
    port = 8010
    api:
        get: /database/product/all
        get: /database/product/{asin}
        get: /database/status/all
        get: /database/status/add

order:
    port = 8004
    api:
        get:  /order
        post: /order/new (requestBody = cart)