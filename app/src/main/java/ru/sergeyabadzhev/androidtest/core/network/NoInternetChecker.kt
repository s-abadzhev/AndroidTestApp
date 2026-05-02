package ru.sergeyabadzhev.androidtest.core.network

fun Exception.isNoInternetException(): Boolean =
    this is java.net.UnknownHostException