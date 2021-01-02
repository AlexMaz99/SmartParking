package pl.edu.agh.smartparking

import javafx.beans.property.SimpleBooleanProperty
import tornadofx.Controller

class MainViewController: Controller(){
    val visibility = mutableListOf<SimpleBooleanProperty>()

    init {
        for(i in 0 until Configuration.COUNT_OF_PLACES){
            visibility.add(i,SimpleBooleanProperty(false))
        }
    }


    var palces = Configuration.PLACES

    fun setSpecificParking(id:Int,vis: Boolean){
        visibility[id].set(vis)
    }
}