package com.noorganization.googlecertificationkotlin.codelab_material_component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.noorganization.googlecertificationkotlin.codelab_material_component.network.ProductEntry
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.codelab_material_component.ProductCardViewHolder

/**
 * Adapter used to show a simple grid of products.
 */
class ProductCardRecyclerViewAdapter(private val productList: List<ProductEntry>) : RecyclerView.Adapter<ProductCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.shr_product_card, parent, false)
        return ProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductCardViewHolder, position: Int) {
        // TODO: Put ViewHolder binding code here in MDC-102
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
