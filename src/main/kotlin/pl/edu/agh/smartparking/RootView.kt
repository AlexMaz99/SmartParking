package pl.edu.agh.smartparking

import pl.edu.agh.smartparking.mqtt.MQTTConnector
import pl.edu.agh.smartparking.mqtt.MqttCupCarbonMessage.Companion.parseMqttEvent
import tornadofx.View
import tornadofx.vbox
import kotlin.concurrent.thread

class RootView : View() {
    override val root = vbox {
        thread {
            val mqttConnector = MQTTConnector()
            mqttConnector.let {
                it.subscribe("cupcarbon") { topic, message ->
                    val cupCarbonEvent = String(message.payload)
                    when (val mqttEvent = parseMqttEvent(topic, cupCarbonEvent)) {
                        null -> println("Cannot parse event")
                        else -> println(mqttEvent)
                    }
                }
            }
        }
    }


}
