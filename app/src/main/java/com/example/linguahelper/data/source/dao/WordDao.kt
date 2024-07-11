package com.example.linguahelper.data.source.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.linguahelper.data.source.entity.Favourites


@Dao
interface WordDao {
    @Query("select  * from words")
    fun getAllWords(): Cursor

    @Query("SELECT * FROM words WHERE words.word LIKE :query || '%'")
    fun getAllWordsByQuery(query: String): Cursor

    @Query("select * from favouriteTable")
    fun getAllFavourites(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(fav: Favourites)

    @Delete
    fun deleteFavourite(favourites: Favourites)
    @Query("select*from favouriteTable WHERE favouriteTable.word =:name")
    fun getImage1(name:String):Favourites

    @Query("select *from favouriteTable")
    fun getImageWord():List<Favourites>

//    @Query("select * from iamgefavik")
//    fun getAllImageWords():List<ImageFavik>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertImageWord(imageWord: ImageFavik)
//
//    @Delete
//    fun deleteImageView(imageWord: ImageFavik)
}