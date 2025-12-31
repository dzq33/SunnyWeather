package com.sunnyweather.android.logic.modle

import com.google.gson.annotations.SerializedName


data class DailyResponse(val status: String,val result: Result){
//    data class Result(val daily:Daily)
//    data class Daily(val temperature: List<Temperature>,val skycon:List<Skycon>,@SerializedName("life_index")val lifeIndex:List<LifeIndex>)
//    data class Temperature(val max: Float,val min: Float)
//    data class Skycon(val value: String,val data: String)
//    data class LifeIndex(val coldRisk:List<LifeDescription>,val carWashing:List<LifeDescription>,val ultraviolet:List<LifeDescription>,val dressing:List<LifeDescription>)
//
//    data class LifeDescription(val desc: String)
data class Result(
    val daily: Daily
)

    data class Daily(
        // 温度预报列表
        val temperature: List<Temperature>,
        // 天气现象列表
        val skycon: List<Skycon>,
        // 生活指数 - 注意：这是一个对象，不是列表！
        @SerializedName("life_index")
        val lifeIndex: LifeIndex
    )

    // 温度预报（每天的最高最低温）
    data class Temperature(
        val date: String,  // 日期，如 "2023-10-01"
        val max: Float,
        val min: Float
    )

    // 天气现象
    data class Skycon(
        val date: String,  // 日期
        val value: String  // 天气类型，如 "CLEAR_DAY"
    )

    // 生活指数对象 - 这里修正了核心错误
    data class LifeIndex(
        // 每个指数都是一个对象，包含描述列表
        val coldRisk: List<IndexDescription>,
        val carWashing: List<IndexDescription>,
        val ultraviolet: List<IndexDescription>,
        val dressing: List<IndexDescription>
        // 可能还有其他指数，如 "comfort", "sport" 等
    )

    // 指数描述
    data class IndexDescription(
        val date: String,  // 日期
        val desc: String,  // 描述文本，如 "易发"
        val index: String  // 指数等级，如 "较低"
    )
}

