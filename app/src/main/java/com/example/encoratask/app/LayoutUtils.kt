package com.example.encoratask.app

import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.encoratask.R
import com.google.android.material.snackbar.Snackbar

class LayoutUtils {
    companion object {

        fun showSnack(view: View, message: String) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.setTextColor(ResourcesCompat.getColor(view.context.resources, R.color.colorPrimary,null))
            snackbar.show()
        }
    }
}

