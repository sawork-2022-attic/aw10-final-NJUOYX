## 系统设计描述

### database
mysql数据库存储了大量的商品数据，以及用户的订单数据。database 服务提供了读商品对象和读写订单数据的方法。
对象分别被包装为Mono和Flux，以融入响应式架构。

### product

提供了读取和搜索商品对象的方法，底层使用了database服务。

### cart

使用redis提供了购物车缓存，并且能为不同用户提供购物车容器，以uid为key区分。
当用户进行结算时，会将用户的购物车使用rabbitmq消息中间件传递cart信息。

### order
接收rabbitmq消息中间件的cart信息，并且以cart消息驱动记录用户的订单。
订单的查看和写入通过调用database服务完成。

### gateway/discovery
提供了统一的系统调用路径

### client

提供了shell客户端。客户端通过webclient对系统的服务进行调用。
应用响应式架构，shell客户端提供了非阻塞的命令实现，通过订阅的方式获取服务端数据，
并且对可能返回大量数据的服务调用使用了backpressure，控制返回数量。

## test

client加载命令脚本完成一系列操作。通过启动多个client进程模拟多用户请求。
结果能够证明系统运行正常并且相应速度在数毫秒内。