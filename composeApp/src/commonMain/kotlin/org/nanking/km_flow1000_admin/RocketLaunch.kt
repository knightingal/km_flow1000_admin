package org.nanking.km_flow1000_admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RocketLaunch (
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?,
)