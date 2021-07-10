package com.example.encoratask.featureGetCharacter.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.encoratask.app.RYMApp
import com.example.encoratask.core.model.Character
import com.example.encoratask.databinding.ActivityDetailBinding
import com.example.encoratask.featureGetCharacter.viewmodels.ItemDetailViewModel

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val vm: ItemDetailViewModel by lazy {
        ItemDetailViewModel.ItemDetailViewModelFactory((application as RYMApp).getCharacter)
            .create(ItemDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val character = intent.getParcelableExtra<Character>("character")
        vm.getCharacterFromExtra(character)
        binding.lifecycleOwner = this
        binding.vm =vm
    }
}