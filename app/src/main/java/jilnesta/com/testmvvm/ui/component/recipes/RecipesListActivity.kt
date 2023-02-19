package jilnesta.com.testmvvm.ui.component.recipes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.databinding.ActivityRecipesListBinding
import jilnesta.com.testmvvm.ui.base.BaseActivity

class RecipesListActivity : BaseActivity() {

    private lateinit var binding: ActivityRecipesListBinding

    private val recipesListViewModel: RecipesListViewModel by viewModels()
    override fun observeViewModel() {

    }

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
    }
}