package com.example.carbrowser.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.carbrowser.DAO.ProfileDAO
import com.example.carbrowser.enty.Profile

@Database(entities= arrayOf(Profile::class), version=1, exportSchema = false)
abstract class ProfileDatabase: RoomDatabase() {

    companion object{
        // Prevent duplicate database instance
        @Volatile
        var profileDatabaseInstance: ProfileDatabase? = null

        fun getDatabase(context: Context):ProfileDatabase{
            // Create database if there's no instance, otherwise used the existence database
            return profileDatabaseInstance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "insects_table"
                ).build()
                profileDatabaseInstance = instance
                return instance
            }
        }
    }

    abstract  fun profileDao(): ProfileDAO
}