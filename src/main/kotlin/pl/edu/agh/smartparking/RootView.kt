package pl.edu.agh.smartparking

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.image.Image
import javafx.scene.image.PixelReader
import pl.edu.agh.smartparking.mqtt.MQTTConnector
import pl.edu.agh.smartparking.mqtt.MqttCupCarbonMessage.Companion.parseMqttEvent
import tornadofx.*
import java.io.File
import java.io.InputStream
import kotlin.concurrent.thread

class RootView : View() {
    val mainViewController: MainViewController by inject()

    override val root = vbox {

        prefWidth = Configuration.WIDTH
        prefHeight = Configuration.HEIGHT


        style {
            backgroundImage += File("src/main/kotlin/pl/edu/agh/smartparking/images/plan.jpg").toURI()
        }

        pane {
            for(i in 0 until Configuration.COUNT_OF_PLACES){
                imageview {
                    visibleProperty().bind(mainViewController.visibility[i])
                    image = Image("file:src/main/kotlin/pl/edu/agh/smartparking/images/car.jpg")
                    this.x = mainViewController.palces[i].first
                    this.y = mainViewController.palces[i].second
                }
            }

        }


//        thread {
//            val mqttConnector = MQTTConnector()
//            mqttConnector.let {
//                it.subscribe("cupcarbon") { topic, message ->
//                    val cupCarbonEvent = String(message.payload)
//                    when (val mqttEvent = parseMqttEvent(topic, cupCarbonEvent)) {
//                        null -> println("Cannot parse event")
//                        else -> println(mqttEvent)
//                    }
//                }
//            }
//        }
    }


}
