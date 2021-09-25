package com.example.playground.data.common

sealed class DataSourceException(val messageResource: Any?) : RuntimeException() {
    class Connection(messageResource: Int) : DataSourceException(messageResource)
    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Timeout(messageResource: Int) : DataSourceException(messageResource)
    class Client(messageResource: Int) : DataSourceException(messageResource)
    class Server(messageResource: Any?) : DataSourceException(messageResource)
}
