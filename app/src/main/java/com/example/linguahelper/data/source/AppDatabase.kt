package com.example.linguahelper.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.linguahelper.data.source.dao.WordDao
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.data.source.entity.WordEntity
//, ImageFavik::class

@Database(entities = [WordEntity::class, Favourites::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        private var instance: AppDatabase? = null

        fun init(context: Context) {
            if (instance == null) instance =
                Room.databaseBuilder(context, AppDatabase::class.java, "MyDictionary.db")
                    .allowMainThreadQueries()
                    .createFromAsset("dictionary.db")
                    .build()
        }

        fun getInstance(): AppDatabase = instance!!
    }

}

// bu bizni database create qilishimz