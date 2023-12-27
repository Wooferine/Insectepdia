package com.example.carbrowser.enty
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="insects_table",)
data class Profile(

    @ColumnInfo(name = "Common")
    var common: String,

    @ColumnInfo(name = "Scientific")
    var scientific: String,

    @ColumnInfo(name = "Category")
    var category: String,

    @ColumnInfo(name = "LifeSpan")
    var lifeSpan: Int,

    @ColumnInfo(name = "Description")
    var description: String,

    @ColumnInfo(name = "Images")
    var images: Int

): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


