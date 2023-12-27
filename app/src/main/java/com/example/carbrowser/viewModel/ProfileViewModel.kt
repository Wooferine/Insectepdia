package com.example.carbrowser.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carbrowser.DAO.ProfileDAO
import com.example.carbrowser.database.ProfileDatabase
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application){

    var allProfile: LiveData<List<Profile>>
    private var profileDAO: ProfileDAO
    private var profileRepository: ProfileRepository

    init{
        profileDAO = ProfileDatabase.getDatabase(application).profileDao()
        profileRepository = ProfileRepository(profileDAO)
        allProfile = profileRepository.allProfile
    }

    fun insertProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileRepository.insertProfile(profile)
        }
    }

    fun deleteProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileRepository.deleteProfile(profile)
        }
    }

    fun updateProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileRepository.updateProfile(profile)
        }
    }

}