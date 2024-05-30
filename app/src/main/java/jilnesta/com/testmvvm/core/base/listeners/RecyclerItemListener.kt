package jilnesta.com.testmvvm.core.base.listeners

import jilnesta.com.testmvvm.presentation.component.main.model.RecipesItem

interface RecyclerItemListener {
    fun onItemSelected(recipe: RecipesItem)
}