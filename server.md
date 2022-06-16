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
        post:  /cart
        post: /cart/add (requestBody= item)
        post:  /cart/total
        post: /cart/checkout (requestBody=null)

database:
    port = 8010
    api:
        get: /database/product/all
        get: /database/product/{asin}
        post: /database/status/all
        post: /database/status/add

order:
    port = 8004
    api:
        get:  /order
        post: /order/new (requestBody = cart)