package pl.edu.agh.smartparking

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class MainViewController : Controller() {
    val visibility = mutableListOf<SimpleBooleanProperty>()
    var freePlaces = SimpleIntegerProperty(Configuration.COUNT_OF_PLACES)

    init {
        repeat(Configuration.COUNT_OF_PLACES) { i ->
            visibility.add(i, SimpleBooleanProperty(false))
        }
    }

    var palces = Configuration.PLACES

    fun setSpecificParking(id: Int, vis: Boolean) {
        if (vis and !visibility[id].get())
            freePlaces.set(freePlaces.get() - 1)
        else if (!vis and visibility[id].get())
            freePlaces.set(freePlaces.get() + 1)

        visibility[id].set(vis)
    }
}