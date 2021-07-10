package com.example.encoratask.featureGetList.views

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.encoratask.featureGetList.viewmodels.ItemListViewModel

class ScrollListener(var rv: RecyclerView, val vm : ItemListViewModel) : RecyclerView.OnScrollListener() {
    var load = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(dy>0){
            rv.layoutManager?.let {
                val visibleItemCount = it.childCount
                val totalItemCount = it.itemCount
                val pastVisibleItems = (it as GridLayoutManager).findLastVisibleItemPosition()
                if(vm.dataLoading.value == false){
                    if((visibleItemCount+pastVisibleItems)>= totalItemCount){
                        load = false
                        vm.getListCharacters()
                    }
                }
            }
        }
    }
}