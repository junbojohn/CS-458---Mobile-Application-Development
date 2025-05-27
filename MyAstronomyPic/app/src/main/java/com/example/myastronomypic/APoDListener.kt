package com.example.myastronomypic

interface APoDListener {
    fun processAPoDResult(apod: APoDResult)
    fun processAPoDError(apod: APoDError)
}