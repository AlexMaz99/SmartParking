package pl.edu.agh.smartparking.mqtt

import org.eclipse.paho.client.mqttv3.IMqttClient
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage


/**
 * Created by surjak on 30.12.2020
 */
class MQTTConnector {

    companion object {
        const val SERVER_URL = "tcp://localhost"
        const val PORT = "1883"
    }

    private lateinit var client: IMqttClient

    init {
        connect()
    }

    fun subscribe(topic: String, messageHandler: (receivedTopic: String, message: MqttMessage) -> Unit) {
        client.subscribe("$topic/#") { receivedTopic: String, message ->
            messageHandler(receivedTopic, message)
        }
    }

    fun publishMessage(topic: String, message: String) {
        val mqttMessage = MqttMessage(message.encodeToByteArray())
        client.publish(topic, mqttMessage)
    }

    private fun connect() {
        generateClientIdAndInitializeMqttClient()
        val options = initOptions()
        connectToMqtt(options)
    }

    private fun generateClientIdAndInitializeMqttClient() {
        val generateClientId = MqttClient.generateClientId()

        client = MqttClient("$SERVER_URL:$PORT", generateClientId)
    }

    private fun initOptions(): MqttConnectOptions {
        val options = MqttConnectOptions()
        options.isAutomaticReconnect = true
        options.isCleanSession = true
        options.connectionTimeout = 10
        return options
    }

    private fun connectToMqtt(options: MqttConnectOptions) {
        client.connect(options)
        if (client.isConnected) {
            println("Successfully connected to MQTT broker")
        }
    }
}