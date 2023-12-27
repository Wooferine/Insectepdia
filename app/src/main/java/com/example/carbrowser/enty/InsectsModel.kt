package com.example.carbrowser.enty

sealed class InsectsModel {
    open var insectsCommon: String = ""
    open var insectsScientific: String = ""
    open var insectsCategory: String = ""
    open var insectsLifeSpan: String = ""
    open var insectsDescription: String = ""
    open var insectsImages: Int = 0

    // HomeScreen popular search display
    data class InsectsStats(
        override var insectsCommon: String,
        override var insectsScientific: String,
        override var insectsCategory: String,
        override var insectsLifeSpan: String,
        override var insectsDescription: String,
        override var insectsImages: Int = 0
    ) : InsectsModel()

    // HomeScreen category display
    data class InsectsClassification(
        override var insectsCommon: String,
        override var insectsImages: Int
    ) : InsectsModel()
}