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

        fun parseMqttEvent(topic: String, message: String): MqttCupCarbonMessage? {
            return try {
                tryParse(topic, message)
            } catch (e: Exception) {
                null
            }

        }

        private fun tryParse(topic: String, message: String): MqttCupCarbonMessage {
            val list = topic.split("/").toList()
            val parkingPlaceEvent = ParkingPlaceEvent.fromStr(list[1])
            val floor = toInt(list[2])
            val parkingPlace = toInt(list[3])
            val carId = toInt(message)
            return MqttCupCarbonMessage(parkingPlaceEvent, floor, parkingPlace, carId)
        }

        private fun toInt(str: String): Int {
            return Integer.valueOf(str)
        }
    }

    enum class ParkingPlaceEvent(val str: String) {
        TAKE_PLACE("take"), LEAVE_PLACE("leave");

        fun getName(): String {
            return this.str;
        }

        companion object {
            fun fromStr(str: String): ParkingPlaceEvent {
                return if (str == TAKE_PLACE.getName()) TAKE_PLACE else if (str == LEAVE_PLACE.getName()) LEAVE_PLACE else throw IllegalArgumentException()
            }
        }
    }
}


