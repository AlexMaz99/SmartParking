package pl.edu.agh.smartparking

import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.text.Font
import pl.edu.agh.smartparking.mqtt.MQTTConnector
import pl.edu.agh.smartparking.mqtt.MqttCupCarbonMessage.Companion.parseMqttEvent
import pl.edu.agh.smartparking.mqtt.ParkingPlaceEvent
import tornadofx.*
import java.net.URI
import kotlin.concurrent.thread

class RootView : View() {
    private val mainViewController: MainViewController by inject()

    override val root = hbox {
        vbox {

            prefWidth = 280.0
            prefHeight = Configuration.HEIGHT

            style {
                backgroundColor += Color.WHITE
            }

            pane {

                label {
                    text = "Free places: "
                    this.font = Font("Arial", 16.0)
                    this.layoutX = 10.0
                    this.layoutY = 0.0
                }

                label {
                    textProperty().bind(mainViewController.freePlaces.asString())
                    this.font = Font("Arial", 16.0)
                    this.layoutX = 120.0
                    this.layoutY = 0.0
                }

                label {
                    textProperty().bind(mainViewController.statistics.history)
                    this.font = Font("Arial", 16.0)
                    this.layoutX = 10.0
                    this.layoutY = 40.0
                }
            }
        }

        vbox {

            prefWidth = Configuration.WIDTH
            prefHeight = Configuration.HEIGHT


            style {
                backgroundImage += URI("images/plan.jpg")
            }

            pane {
                repeat(Configuration.COUNT_OF_PLACES) { i ->
                    imageview {
                        visibleProperty().bind(mainViewController.visibility[i])
                        image = Image("/images/car.jpg")
                        this.x = mainViewController.places[i].first
                        this.y = mainViewController.places[i].second
                    }
                }
            }


            thread {
                MQTTConnector().subscribe("cupcarbon") { topic, message ->
                    val cupCarbonEvent = String(message.payload)
                    when (val mqttEvent = parseMqttEvent(topic, cupCarbonEvent)) {
                        null -> println("Cannot parse event")
                        else -> {
                            println(mqttEvent)
                            runLater {
                                changePlaceState(
                                    mqttEvent.parkingPlace,
                                    mqttEvent.parkingPlaceEvent == ParkingPlaceEvent.TAKE_PLACE,
                                    mqttEvent.carId
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun changePlaceState(place: Int, boolean: Boolean, car: Int) =
        mainViewController.setSpecificParking(place, boolean, car)

}
