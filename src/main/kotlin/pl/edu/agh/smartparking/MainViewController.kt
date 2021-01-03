package pl.edu.agh.smartparking

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class MainViewController : Controller() {
    val visibility = mutableListOf<SimpleBooleanProperty>()
    var freePlaces = SimpleIntegerProperty(Configuration.COUNT_OF_PLACES)
    var statistics = Statistics()

    init {
        repeat(Configuration.COUNT_OF_PLACES) { i ->
            visibility.add(i, SimpleBooleanProperty(false))
        }
    }

    var places = Configuration.PLACES

    fun setSpecificParking(place: Int, vis: Boolean, car: Int) {
        if (vis and !visibility[place].get()) {
            freePlaces.set(freePlaces.get() - 1)
            statistics.saveStartTime(place)
        } else if (!vis and visibility[place].get()) {
            freePlaces.set(freePlaces.get() + 1)
            statistics.saveTimeToHistory(place, car)
        }

        visibility[place].set(vis)
    }
}