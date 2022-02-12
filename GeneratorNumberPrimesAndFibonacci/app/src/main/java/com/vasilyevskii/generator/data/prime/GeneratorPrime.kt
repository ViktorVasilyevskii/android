package com.vasilyevskii.generator.data.prime

typealias PrimeListener = (listElementsPrimes: List<String>) -> Unit

class GeneratorPrime {

    private val step = 5000
    private var prime = 2
    private var primeLong = 0L

    private var firstPrimeLong = true


    private val primeNumbers = PrimeNumbers()

    private var listElementsPrimes = mutableListOf<String>()
    private val listeners = mutableListOf<PrimeListener>()

    init{
        generatorElements()
    }

    fun addListener(listener: PrimeListener){
        listeners.add(listener)
        listener.invoke(listElementsPrimes)
    }

    fun removeListener(listener: PrimeListener){
        listeners.remove(listener)
    }

    fun generatorElements(){

            if (prime + step <= Int.MAX_VALUE) {
                listElementsPrimes.addAll(elements(prime + step))
            }else {
                if (firstPrimeLong) {
                    primeLong = prime.toLong()
                }
                if(primeLong + step <= Long.MAX_VALUE) {
                    firstPrimeLong = false
                    listElementsPrimes.addAll(elements(primeLong + step))
                }else{
                    prime = 2
                    firstPrimeLong = true
                }
            }
        notifyChanges()
    }



    private fun notifyChanges(){
        listeners.forEach{it.invoke(listElementsPrimes)}
    }

    private fun elements(countElements: Int): List<String>{
        val listInt = ArrayList<String>()

        for(indexPrime in prime..countElements){
            if(primeNumbers.isPrime(indexPrime)){
                listInt.add(indexPrime.toString())
            }
        }

        prime = countElements
        return listInt
    }



    private fun elements(countElements: Long): List<String>{
        val listInt = ArrayList<String>()

        for(indexPrime in prime..countElements){
            if(primeNumbers.isPrime(indexPrime)){
                listInt.add(indexPrime.toString())
            }
        }

        primeLong = countElements
        return listInt
    }

}