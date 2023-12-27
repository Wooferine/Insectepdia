package com.example.carbrowser
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.viewModel.ProfileViewModel

class AdminAddProfileScreen: AppCompatActivity() {
    private var SAVE = false

    // Mapping category type to default image
    // Lack of image input feature will catch exception when insufficient arguments
    private var temporaryImages: Map<String, Int> = mapOf(
        "Hemiptera" to R.drawable.hemiptera_wire,
        "Lepidoptera" to R.drawable.lepidoptera_wire,
        "Diptera" to R.drawable.diptera_wire,
        "Hymenoptera" to R.drawable.hymenoptera_wire,
        "Coleoptera" to R.drawable.coleoptera_wire,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_profile)


        var profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ProfileViewModel::class.java)

        findViewById<Button>(R.id.submit).setOnClickListener{
            insertProfileToDatabase(profileViewModel)

            if(SAVE){
                var intent = Intent(this@AdminAddProfileScreen, AdminHomeScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun insertProfileToDatabase(viewModel: ProfileViewModel){

        var commonName: String = findViewById<TextView>(R.id.editCommon).text.toString()
        var scientificName: String = findViewById<TextView>(R.id.editScientific).text.toString()
        val lifeSpan: String = findViewById<TextView>(R.id.editLifeSpan).text.toString().takeIf { it.isNotEmpty() } ?: ""
        var description: String = findViewById<TextView>(R.id.editDescription).text.toString()
        var radioButtonId: Int = findViewById<RadioGroup>(R.id.editCategory).checkedRadioButtonId

        // Check unselected radio group
        var category = ""
        if(!radioButtonId.equals(-1)){
            category = findViewById<RadioButton>(radioButtonId).text.toString()
        }

        // Check unfilled input
        if(!editTextCheck(arrayListOf(commonName, scientificName, category, lifeSpan, description))) run {
            // Create profile object
            var profile = Profile(commonName, scientificName, category, Integer.parseInt(lifeSpan), description, temporaryImages[category]!!)
            viewModel.insertProfile(profile)
            Toast.makeText(
                this,
                "Profile is added to local database.",
                Toast.LENGTH_LONG
            ).show()

            // Set flags to execute intend
            SAVE = true
        }else{
            Toast.makeText(
                this,
                "Empty field detected.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun editTextCheck(stringList: ArrayList<Any>): Boolean{
        var flag = true
        // Check for empty variables
        for(item in stringList){
            flag = TextUtils.isEmpty(item.toString())
        }
        return flag
    }

}