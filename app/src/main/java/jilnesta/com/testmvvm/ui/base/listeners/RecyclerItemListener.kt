package jilnesta.com.testmvvm.ui.base.listeners

import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem

interface RecyclerItemListener {
    fun onItemSelected(recipe: RecipesItem)
}