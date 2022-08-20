package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    private var _score = MutableLiveData<Int>()
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val score: LiveData<Int>
        get()= _score
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain
    init{
        _score.value = finalScore

    }
    fun onPlayAgain(){
        _eventPlayAgain.value=true
    }
    fun onPlayAgainComplete(){
        _eventPlayAgain.value=false
    }
}