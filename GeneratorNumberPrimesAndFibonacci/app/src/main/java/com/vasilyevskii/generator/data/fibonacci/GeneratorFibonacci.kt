package com.vasilyevskii.generator.data.fibonacci

import android.util.Log
import java.math.BigInteger

typealias FibonacciListener = (listElementsFibonacci: List<BigInteger>) -> Unit

class GeneratorFibonacci {

    private val step = 100
    private var startNumber = 2
    private var endNumber = step

    private var listElementsFibonacci = mutableListOf<BigInteger>()
    private val listeners = mutableListOf<FibonacciListener>()

    init {
        Log.d("GeneratorFibonacci", "startNumber = $startNumber | endNumber = $endNumber")
        initData()
    }

    fun addListener(listener: FibonacciListener){
        listeners.add(listener)
        listener.invoke(listElementsFibonacci)
    }

    fun removeListener(listener: FibonacciListener){
        listeners.remove(listener)
    }

    fun startGenerator(){
        if((endNumber + step) < Int.MAX_VALUE){
            generatorFibonacciElements((endNumber + 1), (endNumber + step))
        }else {
            endNumber = step
            initData()
        }
    }

    private fun generatorFibonacciElements(startNumber: Int, endNumber: Int){
        for(indexElementFibonacci in startNumber..endNumber){
            listElementsFibonacci.add(
                listElementsFibonacci[indexElementFibonacci - 1]
                .add(listElementsFibonacci[indexElementFibonacci - 2]))
        }
        notifyChanges()
    }

    private fun notifyChanges(){
        listeners.forEach{it.invoke(listElementsFibonacci)}
    }

    private fun initData(){
        listElementsFibonacci.add(BigInteger.ZERO)
        listElementsFibonacci.add(BigInteger.ONE)
        generatorFibonacciElements(startNumber, endNumber)
    }
}