package jilnesta.com.testmvvm.ui.component.recipes

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.recipes.Recipes
import jilnesta.com.testmvvm.databinding.ActivityRecipesListBinding
import jilnesta.com.testmvvm.ui.base.BaseActivity
import jilnesta.com.testmvvm.ui.component.recipes.adapter.RecipesAdapter
import jilnesta.com.testmvvm.utils.observe
import jilnesta.com.testmvvm.utils.toGone
import jilnesta.com.testmvvm.utils.toVisible

@AndroidEntryPoint
class RecipesListActivity : BaseActivity() {

    private lateinit var binding: ActivityRecipesListBinding

    private val recipesListViewModel: RecipesListViewModel by viewModels()

    private lateinit var recipesAdapter: RecipesAdapter

    override fun initViewBinding() {
        binding = ActivityRecipesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.recipe)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
        recipesListViewModel.getRecipes()
    }

    override fun observeViewModel() {
        observe(recipesListViewModel.recipesLiveData, ::handleRecipesList)
    }

    private fun handleRecipesList(status: Resource<Recipes>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { recipesListViewModel.showToastMessage(it) }
            }
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }

    private fun bindListData(recipes: Recipes) {
        if(recipes.recipesList.isNotEmpty()) {
            recipesAdapter = RecipesAdapter(recipesListViewModel, recipes.recipesList)
            binding.rvRecipesList.adapter = recipesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if(show) GONE else VISIBLE
        binding.rvRecipesList.visibility = if(show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }
}