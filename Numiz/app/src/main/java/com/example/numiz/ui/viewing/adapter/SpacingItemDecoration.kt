package com.example.numiz.ui.viewing.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spacing;
        outRect.right = spacing;
        outRect.bottom = spacing;
        var id = parent.getChildLayoutPosition(view)
        if (id == 0) {
            outRect.top = spacing
        }
    }
}