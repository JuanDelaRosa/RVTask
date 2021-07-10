package com.example.encoratask.featureGetList.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.encoratask.app.LayoutUtils
import com.example.encoratask.app.RYMApp
import com.example.encoratask.databinding.ActivityListBinding
import com.example.encoratask.featureGetCharacter.views.ItemDetailActivity
import com.example.encoratask.featureGetList.viewmodels.ItemListViewModel


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    private val binding: ActivityListBinding by lazy {
        ActivityListBinding.inflate(
            layoutInflater
        )
    }
    private val vm: ItemListViewModel by lazy {
        ItemListViewModel.ItemListViewModelFactory((application as RYMApp).getList)
            .create(ItemListViewModel::class.java)
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.reciclerv.addOnScrollListener(ScrollListener(binding.reciclerv,vm))
        binding.reciclerv.adapter = ItemListAdapter{
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("character", it)
            startActivity(intent)
        }
        vm.getListCharacters()
        vm.list.observe(this, {characters ->
            characters?.let {
                (binding.reciclerv.adapter as ItemListAdapter).setData(it)
            }
        })
        vm.error.observe(this, {
            LayoutUtils.showSnack(binding.root, it)
        })

        vm.dataLoading.observe(this, {
            binding.pbLoading.visibility = if(it==true){
                View.VISIBLE
            }else
                View.GONE
        })

    }
}
