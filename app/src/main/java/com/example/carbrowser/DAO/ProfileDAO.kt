package com.example.carbrowser.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.carbrowser.enty.Profile

@Dao
interface ProfileDAO {

    @Query("SELECT *FROM insects_table ORDER BY ID ASC")
    fun allProfile(): LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfile(profile: Profile)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)


}