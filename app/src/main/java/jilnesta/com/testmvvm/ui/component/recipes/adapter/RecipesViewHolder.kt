package jilnesta.com.testmvvm.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import jilnesta.com.testmvvm.databinding.RecipeItemBinding
import jilnesta.com.testmvvm.ui.base.listeners.RecyclerItemListener

class RecipesViewHolder(private val itemBinding: RecipeItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = recipesItem.description
        itemBinding.tvName.text = recipesItem.name
        Picasso.get().load(recipesItem.thumb).placeholder(R.drawable.ic_healthy_food)
            .error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener {
            recyclerItemListener.onItemSelected(
                recipesItem
            )
        }
    }
}