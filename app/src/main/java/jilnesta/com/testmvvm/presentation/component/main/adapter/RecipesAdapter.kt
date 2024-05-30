package jilnesta.com.testmvvm.presentation.component.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jilnesta.com.testmvvm.databinding.RecipeItemBinding
import jilnesta.com.testmvvm.core.base.listeners.RecyclerItemListener
import jilnesta.com.testmvvm.presentation.component.main.model.RecipesItem
import jilnesta.com.testmvvm.presentation.component.main.vm.MainViewModel

class RecipesAdapter(
    private val recipesListViewModel: MainViewModel,
    private val recipes: List<RecipesItem>
) : RecyclerView.Adapter<RecipesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemBinding =
            RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: RecipesItem) {
            recipesListViewModel.openRecipeDetails(recipe)
        }
    }
}