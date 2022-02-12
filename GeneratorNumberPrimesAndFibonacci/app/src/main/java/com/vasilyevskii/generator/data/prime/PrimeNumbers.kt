package com.vasilyevskii.generator.data.prime

class PrimeNumbers {

    private val zeroInt = 0
    fun isPrime(number: Int): Boolean{
        var indexNumber = 5
        if(number < 2){
            return false
        }
        if(number % 2 == zeroInt){
            return number == 2
        }
        if(number % 3 == zeroInt){
            return number == 3
        }

        while(indexNumber * indexNumber <= number){
            if(number % indexNumber == zeroInt ||  number % (indexNumber + 2) == zeroInt){
                return false
            }
            indexNumber += 6
        }
        return true
    }

    private val zeroLong = 0L
    fun isPrime(number: Long): Boolean{
        var indexNumberLong = 5L
        while(indexNumberLong * indexNumberLong <= number){
            if(number % indexNumberLong == zeroLong ||  number % (indexNumberLong + 2L) == zeroLong){
                return false
            }
            indexNumberLong += 6L
        }

        return true
    }

}