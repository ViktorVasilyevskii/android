package com.vasilyevskii.generator

import android.app.Application
import com.vasilyevskii.generator.data.fibonacci.GeneratorFibonacci
import com.vasilyevskii.generator.data.prime.GeneratorPrime

class App : Application() {

    val generatorPrime = GeneratorPrime()
    val generatorFibonacci = GeneratorFibonacci()
}