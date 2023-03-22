package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        var start = 10
        var currentvalue = start
        emit(start)

        while (currentvalue > 0) {
            delay(1000L)
            currentvalue--
            emit(currentvalue)
        }
    }

    init {
        collectData()
    }



    private fun collectData() {
        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time->
                    time*time
                }
                .onEach {time->
                    println("time is $time")
                }
                .collect{time->

                    println("time is $time")
                }
                //.launchIn(viewModelScope)

        }

    }
}