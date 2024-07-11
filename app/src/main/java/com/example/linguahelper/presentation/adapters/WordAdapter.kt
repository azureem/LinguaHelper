package com.example.linguahelper.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.linguahelper.R
import com.example.linguahelper.data.MyMapper.toData
import com.example.linguahelper.data.model.WordData
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.data.source.entity.WordEntity
import com.example.linguahelper.databinding.ItemDictionaryBinding
import com.example.linguahelper.databinding.ScreenWordBinding
import com.example.linguahelper.domain.AppRepository
import com.example.linguahelper.domain.AppRepositoryImpl
import com.example.linguahelper.utils.createSpannable
import java.util.Locale


// bu yerda recycler adapterda foydalanyapmiz, list adapterdanemas, chuki list adapterda diffutil bored unda ikkita listni tekshirardik,
//lekin bu yerda bizda bazadan list emas cursor qaytkanligi sabab listadapter ishlata olmaymiz

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    private var cursor: Cursor? = null
    private var query: String? = null
    private var wordOnClick: ((WordEntity) -> Unit)? = null
    private var saveOnClick: ((Favourites) -> Unit)? = null
    private val repo: AppRepository = AppRepositoryImpl.getAppRepository()
    private val data: WordData? = null
    private var pos = 0;
    private var image = ""
    val wordEntity = ""
    private var list = repo.getAllFavourite()


    @SuppressLint("Range")
    inner class WordViewHolder(private val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: WordData) {
            if (query == null) binding.textWord.text = data.word
            else binding.textWord.text = data.word.createSpannable(query!!)
            binding.textType.text = "[${data.type}]"
            if (repo.getImage1(data.word) != null) {
                binding.favouriteBox.setImageResource(R.drawable.soxr)
            } else {
                binding.favouriteBox.setImageResource(R.drawable.nesox)
            }
        }

        init {
            itemView.setOnClickListener {
                cursor?.let {
                    it.moveToPosition(adapterPosition)
                    val wordEntity = WordEntity(
                        word = it.getString(it.getColumnIndex("word")),
                        type = it.getString(it.getColumnIndex("type")),
                        sdex = it.getString(it.getColumnIndex("sdex")),
                        wlen = it.getInt(it.getColumnIndex("wlen")),
                        defn = it.getString(it.getColumnIndex("defn")),
                    )
                    wordOnClick?.invoke(wordEntity)
                }
            }


            binding.favouriteBox.setOnClickListener {
                binding.favouriteBox.setImageResource(R.drawable.soxr)
                cursor?.let {
                    it.moveToPosition(absoluteAdapterPosition)
                    val fav = Favourites(
                        id = 0,
                        word = it.getString(it.getColumnIndex("word")),
                        type = it.getString(it.getColumnIndex("type")),
                        defn = it.getString(it.getColumnIndex("defn")),
                        fav = 1
                    )
                    image = it.getString(it.getColumnIndex("word"))
                    Boolean(absoluteAdapterPosition.toLong(), image)

                    if (!Boolean(absoluteAdapterPosition.toLong(), image)) {
                        binding.favouriteBox.setImageResource(R.drawable.soxr)

                        saveOnClick?.invoke(fav)
                    } else {
                        binding.favouriteBox.setImageResource(R.drawable.nesox)
                        val name = True(absoluteAdapterPosition.toLong(), image)
                        if (name != null) {
                            repo.deleteFavourite(name)
                        }
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder = WordViewHolder(
            ItemDictionaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return if (cursor == null) 0 else cursor!!.count
    }


    @SuppressLint("Range")
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        cursor?.let {
            it.moveToPosition(position)
            val wordEntity = WordEntity(
                word = it.getString(it.getColumnIndex("word")),
                type = it.getString(it.getColumnIndex("type")),
                sdex = it.getString(it.getColumnIndex("sdex")),
                wlen = it.getInt(it.getColumnIndex("wlen")),
                defn = it.getString(it.getColumnIndex("defn")),
            )
            holder.bind(wordEntity.toData())
        }

    }

    fun setCursor(cursor: Cursor, query: String? = null) {
        this.cursor?.close()
        this.cursor = cursor
        this.query = query
        notifyDataSetChanged()
    }


    fun setOnWordClickListener(listener: (WordEntity) -> Unit) {
        this.wordOnClick = listener
    }


    fun saveOnClickedFav(click: (Favourites) -> Unit) {
        this.saveOnClick = click
    }

    fun Boolean(index: Long, name: String): Boolean {
        cursor.let {
            it?.moveToPosition(index.toInt())

            for (i in 0 until repo.getImageWord().size) {
                if ((repo.getImageWord())[i].word == name) {
                    return true
                }
            }
        }
        return false
    }

    fun True(index: Long, name: String): Favourites? {
        cursor.let {
            it?.moveToPosition(index.toInt())

            for (i in 0 until repo.getImageWord().size) {
                if ((repo.getImageWord())[i].word == name) {
                    return repo.getImageWord()[i]
                }
            }
        }
        return null
    }


}