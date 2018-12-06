package com.keiferstone.nonet

data class Config(
        /**
         * The url to ping
         */
        var url: String = "http://gstatic.com/generate_204",
        /**
         * The amount of time to wait before failing
         */
        var timeout: Long = 5,
        /**
        * The interval in seconds between requests to [url] when connected
        */
        var connectedPollInterval: Long = 60,
        /**
         * The interval in seconds between requests to [url] when not connected
         */
        var disconnectedPollInterval: Long = 5)

fun config(init: Config.() -> Unit): Config {
    val config = Config()
    config.init()
    return config
}