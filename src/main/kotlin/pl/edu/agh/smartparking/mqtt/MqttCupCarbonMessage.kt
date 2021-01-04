package pl.edu.agh.smartparking.mqtt

import java.lang.Exception
import java.lang.IllegalArgumentException


/**
 * Created by surjak on 30.12.2020
 */
// CupCarbon IotNode will publish a message like "cupcarbon/{take or leave}/{floor}/{parkingPlace}" and message will be {carId}
data class MqttCupCarbonMessage(
    val parkingPlaceEvent: ParkingPlaceEvent,
    val floor: Int,
    val parkingPlace: Int,
    val carId: Int
) {
    companion object {

        fun parseMqttEvent(topic: String, message: String): MqttCupCarbonMessage? = try {
            tryParse(topic, message)
        } catch (e: Exception) {
            null
        }

        private fun tryParse(topic: String, message: String): MqttCupCarbonMessage {
            val list = topic.split("/").toList()
            val parkingPlaceEvent = ParkingPlaceEvent.fromStr(list[1])
            val floor = list[2].toInt()
            val parkingPlace = list[3].replace("IOT","").toInt()
            val carId = message.toInt()
            return MqttCupCarbonMessage(parkingPlaceEvent, floor, parkingPlace, carId)
        }

    }

}

enum class ParkingPlaceEvent(val str: String) {
    TAKE_PLACE("take"), LEAVE_PLACE("leave");

    companion object {
        fun fromStr(str: String): ParkingPlaceEvent = when (str) {
            TAKE_PLACE.str -> TAKE_PLACE
            LEAVE_PLACE.str -> LEAVE_PLACE
            else -> throw IllegalArgumentException()
        }
    }
}


