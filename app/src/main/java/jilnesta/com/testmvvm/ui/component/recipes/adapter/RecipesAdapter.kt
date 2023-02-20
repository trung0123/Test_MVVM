package jilnesta.com.testmvvm.ui.component.recipes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import jilnesta.com.testmvvm.ui.component.recipes.RecipesListViewModel

class RecipesAdapter(
    private val recipesListViewModel: RecipesListViewModel,
    private val recipes: List<RecipesItem>
) : RecyclerView.Adapter<RecipesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}