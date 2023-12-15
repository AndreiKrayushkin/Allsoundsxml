package com.krayushkindev.allsoundsxml.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krayushkindev.allsoundsxml.databinding.SoundItemBinding
import com.krayushkindev.allsoundsxml.model.SoundModel

class SoundsAdapter: RecyclerView.Adapter<SoundsAdapter.ViewHolder>() {

    private var allSounds: List<SoundModel> = emptyList()
    lateinit var onSoundClick: (thisSound: SoundModel) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun initSounds(allSounds: List<SoundModel>) {
        this.allSounds = allSounds
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: SoundItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sound: SoundModel) {
            binding.apply {
                soundTitle.text = sound.audioName

                root.setOnClickListener {
                    onSoundClick.invoke(sound)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(SoundItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = allSounds.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allSounds[position])
    }
}