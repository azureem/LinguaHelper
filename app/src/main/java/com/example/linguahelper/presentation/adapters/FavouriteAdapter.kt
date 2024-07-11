package com.example.linguahelper.presentation.adapters

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linguahelper.R
import com.example.linguahelper.data.MyMapper.toData
import com.example.linguahelper.data.model.WordData
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.data.source.entity.WordEntity
import com.example.linguahelper.databinding.ItemDictionaryBinding
import com.example.linguahelper.databinding.ItemFavikBinding
import com.example.linguahelper.utils.createSpannable

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private var cursor: Cursor? = null
    private var query: String? = null
    private var savedOnClick: ((Favourites) -> Unit)? = null
    private var saveOnClick: ((Favourites) -> Unit)? = null


    @SuppressLint("Range")
    inner class FavouriteViewHolder(private val binding: ItemFavikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Favourites) {
            binding.textWord.text = data.word
           // else binding.textWord.text = data.word.createSpannable(query!!)
            binding.textType.text = "[${data.type}]"
        }

        init {
            itemView.setOnClickListener {
                cursor?.let {
                    it.moveToPosition(adapterPosition)
                    val fav = Favourites(
                        id = 0,
                        word = it.getString(it.getColumnIndex("word")),
                        type = it.getString(it.getColumnIndex("type")),
                        defn = it.getString(it.getColumnIndex("defn")),
                    )
                    saveOnClick?.invoke(fav)
                }
            }

            binding.favouriteBox.setOnClickListener {
                cursor?.let {
                    it.moveToPosition(absoluteAdapterPosition)
                    val fav = Favourites(
                        id = it.getLong(it.getColumnIndex("id")),
                        word = it.getString(it.getColumnIndex("word")),
                        type = it.getString(it.getColumnIndex("type")),
                        defn = it.getString(it.getColumnIndex("defn"))
                    )
                    savedOnClick?.invoke(fav)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder =
        FavouriteViewHolder(
            ItemFavikBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return if (cursor == null) 0 else cursor!!.count
    }


    @SuppressLint("Range")
    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        cursor?.let {
            it.moveToPosition(position)
            val fav = Favourites(
                id = 0,
                word = it.getString(it.getColumnIndex("word")),
                type = it.getString(it.getColumnIndex("type")),
                defn = it.getString(it.getColumnIndex("defn")),
            )
            holder.bind(fav)
        }

    }

    fun setCursor(cursor: Cursor, query: String? = null) {
        this.cursor?.close()
        this.cursor = cursor
        this.query = query
        notifyDataSetChanged()
    }


    fun setOnSavedFun(listener: (Favourites) -> Unit) {
        this.savedOnClick = listener
    }

    fun saveOnClickedFav(click: (Favourites) -> Unit) {
        this.saveOnClick = click
    }

}