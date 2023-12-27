package com.example.carbrowser.enty

import android.content.Context
import com.example.carbrowser.R
import kotlin.random.Random

object SampleData {
    var insectsWires: ArrayList<Int> = arrayListOf(
        R.drawable.hemiptera_wire, R.drawable.lepidoptera_wire, R.drawable.diptera_wire,
        R.drawable.hymenoptera_wire, R.drawable.coleoptera_wire,
    )
    var insectsImages: ArrayList<Int> = arrayListOf(
        R.drawable.monarch, R.drawable.mourningcloak, R.drawable.paintedlady,
        R.drawable.pipewine, R.drawable.redadmiral, R.drawable.asianladybeetle,
        R.drawable.fireant, R.drawable.honeybee, R.drawable.queenalexandra,
        R.drawable.brownmarmoratedstinkbug, R.drawable.walkingflowermantis
    )

    var insectsStats: ArrayList<InsectsModel> = ArrayList()
    var insectsClassification: ArrayList<InsectsModel> = ArrayList()
    var popularSearch: ArrayList<InsectsModel> = ArrayList()


    fun createInsectsModel(context: Context){
        // To prevent data duplication
        insectsStats.clear()
        insectsClassification.clear()


        // Get sample string from string.xml
        val insectsModelCommon: Array<String> = context.resources.getStringArray(R.array.insects_common_txt)
        val insectsModelScientific: Array<String> = context.resources.getStringArray(R.array.insects_scientific_txt)
        val insectsModelLifeSpan: Array<String> = context.resources.getStringArray(R.array.insects_life_span_txt)
        val insectsModelCategory: Array<String> = context.resources.getStringArray(R.array.insects_category_txt)
        val insectsModelDescription: Array<String> = context.resources.getStringArray(R.array.insects_description_txt)
        val insectsClasses: Array<String> = context.resources.getStringArray(R.array.insects_classes_txt)

        for(i in insectsModelCommon.indices){
            insectsStats.add(
                InsectsModel.InsectsStats(
                    insectsModelCommon[i],
                    insectsModelScientific[i],
                    insectsModelCategory[i],
                    insectsModelLifeSpan[i],
                    insectsModelDescription[i],
                    insectsImages[i]))
        }
        for(i in insectsClasses.indices){
            insectsClassification.add(InsectsModel.InsectsClassification(insectsClasses[i], insectsWires[i]))
        }

        val randomIntList: ArrayList<Int> = ArrayList(randomNonDuplicateInteger(insectsModelCommon.size))

        for(i in randomIntList){
            popularSearch.add(
                InsectsModel.InsectsStats(
                insectsModelCommon[i],
                insectsModelScientific[i],
                insectsModelCategory[i],
                insectsModelLifeSpan[i],
                insectsModelDescription[i],
                insectsImages[i]))
        }
    }

    private fun randomNonDuplicateInteger(itemSize: Int): HashSet<Int>{
        val generatedNumbersSet = HashSet<Int>()

        // Avoid duplication
        while (generatedNumbersSet.size < 5) {
            val randomNumber = Random.nextInt(0, itemSize)
            if (randomNumber !in generatedNumbersSet) {

                // Add the number to the set to keep track of it
                generatedNumbersSet.add(randomNumber)
            }
        }
        return generatedNumbersSet
    }
}