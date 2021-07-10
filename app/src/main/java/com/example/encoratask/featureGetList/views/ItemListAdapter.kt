package com.example.encoratask.featureGetList.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encoratask.core.model.Character
import com.example.encoratask.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class ItemListAdapter(val click: (Character)-> Unit) : RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {
    var characters : ArrayList<Character> = arrayListOf()
    lateinit var binding : ItemListBinding


    fun setData(list: List<Character>){
        if(!list.isNullOrEmpty()){
            if(characters.isNullOrEmpty())
                characters = list as ArrayList<Character>
            else {
                list.forEach {
                    if(!characters.contains(it))
                        characters.add(it)
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val character = characters[position]

        holder.binding.name.text = character.name
        holder.binding.status.text = character.status
        if(character.image.isNotEmpty())
            Picasso.get().load(character.image).into(holder.binding.image)
        holder.binding.root.setOnClickListener{ click(character)}

    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    class ItemListViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root)
}