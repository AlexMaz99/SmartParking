package pl.edu.agh.smartparking

import javafx.beans.property.SimpleStringProperty

class Statistics {
    var times = mutableListOf<Long>()
    var history = SimpleStringProperty("")

    init {
        repeat(Configuration.COUNT_OF_PLACES) { i ->
            times.add(i, 0L)
        }
    }

    fun saveStartTime(place: Int) {
        times[place] = System.currentTimeMillis()
    }

    fun saveTimeToHistory(place: Int, car: Int) {
        val time = (System.currentTimeMillis() - times[place]) / 1000
        times[place] = 0L
        val description = history.get() + "Place: $place, Time: $time s\n"
        history.set(description)
    }
}