package com.sunnyweather.android.logic.modle

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val status: String,val result: Result){
//    data class Result(val realtime:Realtime)
//
//    data class Realtime(val skycon: String,val temperature: Float,@SerializedName("air_quality")val airQuality:AirQuality)
//    data class AirQuality(val aqi:AQI)
//    data class AQI(val chn: Float)
    data class Result(val realtime: Realtime)
    data class Realtime(
        val status: String,       // "ok"
        val temperature: Float,   // 8.6
        val humidity: Float,      // 0.95
        val cloudrate: Float,     // 1.0
        val skycon: String,       // "LIGHT_RAIN"
        val visibility: Float,    // 12.39
        val dswrf: Float,         // 4.0
        val wind: Wind,
        val pressure: Float,      // 100685.73
        @SerializedName("apparent_temperature")
        val apparentTemperature: Float, // 4.8
        val precipitation: Precipitation,
        @SerializedName("air_quality")
        val airQuality: AirQuality,
        @SerializedName("life_index")
        val lifeIndex: LifeIndex
    )

    // 风
    data class Wind(
        val speed: Float,      // 19.64
        val direction: Float   // 336.44
    )

    // 降水
    data class Precipitation(
        val local: LocalPrecipitation,
        val nearest: NearestPrecipitation
    )

    data class LocalPrecipitation(
        val status: String,     // "ok"
        val datasource: String, // "radar"
        val intensity: Float    // 0.1069
    )

    data class NearestPrecipitation(
        val status: String,   // "ok"
        val distance: Float,  // 0.0
        val intensity: Float  // 0.1875
    )

    // 空气质量 - 根据实际JSON修正
    data class AirQuality(
        val pm25: Int,     // 74
        val pm10: Int,     // 92
        val o3: Int,       // 38
        val so2: Int,      // 1
        val no2: Int,      // 17
        val co: Float,     // 0.8
        val aqi: AqiValue,
        val description: AqiDescription
    )

    data class AqiValue(
        val chn: Int,  // 注意：实际是Int，不是Float
        val usa: Int
    )

    data class AqiDescription(
        val chn: String, // "良"
        val usa: String  // "中度污染"
    )

    // 生活指数 - 根据实际JSON修正
    data class LifeIndex(
        val ultraviolet: LifeIndexItem,
        val comfort: LifeIndexItem
        // 实际JSON中没有coldRisk等
    )

    data class LifeIndexItem(
        val index: Int,     // 0 或 9
        val desc: String    // "无" 或 "寒冷"
    )
}