package com.keiferstone.nonet

data class Config(var url: String = "http://gstatic.com/generate_204",
                  var timeout: Long = 5,
                  var connectedPollInterval: Long = 60,
                  var disconnectedPollInterval: Long = 5)

fun config(init: Config.() -> Unit): Config {
    val config = Config()
    config.init()
    return config
}