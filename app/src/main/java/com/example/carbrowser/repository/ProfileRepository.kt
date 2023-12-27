package com.example.carbrowser.repository

import androidx.lifecycle.LiveData
import com.example.carbrowser.DAO.ProfileDAO
import com.example.carbrowser.enty.Profile

class ProfileRepository(private var profileDAO: ProfileDAO) {

    var allProfile: LiveData<List<Profile>>

    init{
        allProfile = profileDAO.allProfile()

    }

    suspend fun insertProfile(profile: Profile){
        profileDAO.insertProfile(profile)
    }

    suspend fun deleteProfile(profile: Profile){
        profileDAO.deleteProfile(profile)
    }

    suspend fun updateProfile(profile: Profile){
        profileDAO.updateProfile(profile)
    }

}