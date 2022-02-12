package com.vasilyevskii.generator

import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.generator.adapter.RecycleViewAdapter
import com.vasilyevskii.generator.data.fibonacci.FibonacciListener
import com.vasilyevskii.generator.data.fibonacci.GeneratorFibonacci
import com.vasilyevskii.generator.data.prime.GeneratorPrime
import com.vasilyevskii.generator.data.prime.PrimeListener
import java.util.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    private val spanCountGrid = 2
    private val recycleViewAdapter = RecycleViewAdapter()

    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var pastVisiblesItems: Int = 0

    private val indicatorGeneratorPrime = 0
    private val indicatorGeneratorFibonacci = 1

    private lateinit var buttonPrimeNumbers: Button
    private lateinit var buttonFibonacciNumbers: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager


    private val generatorPrime: GeneratorPrime
        get() = (applicationContext as App).generatorPrime

    private val generatorFibonacci: GeneratorFibonacci
        get() = (applicationContext as App).generatorFibonacci

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.list_numbers)
        gridLayoutManager = GridLayoutManager(this, spanCountGrid)
        buttonPrimeNumbers = findViewById(R.id.button_prime_number)
        buttonFibonacciNumbers = findViewById(R.id.button_fibonacci_number)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = recycleViewAdapter

        dataStartApp()
        clickButtonListener()
    }

    private fun dataStartApp(){
        generatorPrime.addListener(primeListener)
        buttonPrimeNumbersPress()
        generatorNumbersScrollListener(indicatorGeneratorPrime)
    }

    private fun buttonPrimeNumbersPress(){
        buttonPrimeNumbers.setBackgroundColor(GRAY)
        buttonFibonacciNumbers.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
    }

    private fun buttonFibonacciNumbersPress(){
        buttonPrimeNumbers.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
        buttonFibonacciNumbers.setBackgroundColor(GRAY)
    }


    private fun clickButtonListener(){

        buttonPrimeNumbers.setOnClickListener {
            generatorFibonacci.removeListener(fibonacciListener)
            generatorPrime.addListener(primeListener)
            buttonPrimeNumbersPress()
            generatorNumbersScrollListener(indicatorGeneratorPrime)
        }

        buttonFibonacciNumbers.setOnClickListener {
            generatorPrime.removeListener(primeListener)
            generatorFibonacci.addListener(fibonacciListener)
            buttonFibonacciNumbersPress()
            generatorNumbersScrollListener(indicatorGeneratorFibonacci)
        }
    }


    private fun generatorNumbersScrollListener(numberGenerator: Int){
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    visibleItemCount = gridLayoutManager.childCount
                    totalItemCount = gridLayoutManager.itemCount
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        if(numberGenerator == 0) {
                            generatorPrime.generatorElements()
                        }else generatorFibonacci.startGenerator()
                    }
                }
            }
        })
    }


    private val primeListener: PrimeListener = {
        recycleViewAdapter.listPrime = it
    }

    private val fibonacciListener: FibonacciListener = {
        recycleViewAdapter.listPrime = it.stream().map(Objects::toString).collect(Collectors.toList())
    }

    override fun onDestroy() {
        super.onDestroy()
        generatorPrime.removeListener(primeListener)
        generatorFibonacci.removeListener(fibonacciListener)
    }


}




