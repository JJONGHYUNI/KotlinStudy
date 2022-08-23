package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    companion object{
        private const val DONE= 0L

        private const val ONE_SECOND = 1000L

        private const val COUNTDOWN_TIME = 60000L
    }

    private val _currentTime=MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get()= _currentTime

    private val timer : CountDownTimer

    val currentTimeString = Transformations.map(currentTime){ time ->
        DateUtils.formatElapsedTime(time)
    }
    // The current word
    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get()= _score
    val word: LiveData<String>
        get()= _word
    val wordHint=Transformations.map(word){word ->
        val randomPosition=(1..word.length).random()
        "Current word has " + word.length + " letters " +
                "\nThe letter at position " +randomPosition +" is "+
                word.get(randomPosition - 1).toUpperCase()
    }
    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish
    init{
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }
        timer.start()
        _word.value = ""
        _score.value=0
        resetList()
        nextWord()
        Log.i("GameViewModel","GameViewModel created!")
    }
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    fun onSkip() {
        _score.value=(score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value=(score.value)?.plus(1)
        nextWord()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        }else{
            _word.value=wordList.removeAt(0)
        }
    }
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
    fun onGameFinish(){
        _eventGameFinish.value=true
    }
    fun onGameFinishComplete(){
        _eventGameFinish.value=false
    }
}