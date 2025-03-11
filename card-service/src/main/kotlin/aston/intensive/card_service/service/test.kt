package aston.intensive.card_service.service

import kotlinx.coroutines.delay

class test {

    fun test() {
        println("test")
        val a: Boolean = (10 < 5) and (5 > 15)
        val list: List<Int> = listOf(1,2,3,4,5)

    }

    suspend fun test2(){
        delay(4999)
        println("test2")
    }
}