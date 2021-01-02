package pl.edu.agh.smartparking

import javafx.beans.property.SimpleBooleanProperty
import tornadofx.Controller

class MainViewController : Controller() {
    val visibility = mutableListOf<SimpleBooleanProperty>()

    init {
        repeat(Configuration.COUNT_OF_PLACES) { i ->
            visibility.add(i, SimpleBooleanProperty(false))
        }
    }

    var palces = Configuration.PLACES

    fun setSpecificParking(id: Int, vis: Boolean) {
        visibility[id].set(vis)
    }
}