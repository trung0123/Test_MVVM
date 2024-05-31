package jilnesta.com.testmvvm.presentation.component.main.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.databinding.ActivityMainBinding
import jilnesta.com.testmvvm.core.base.BaseActivity
import jilnesta.com.testmvvm.presentation.component.home.view.HomeFragment
import jilnesta.com.testmvvm.presentation.component.main.vm.MainViewModel
import jilnesta.com.testmvvm.core.extension.DialogUtil.Companion.hideLoading
import jilnesta.com.testmvvm.core.extension.hideKeyboard

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var container: RelativeLayout

    private lateinit var imgHam: ImageView

    private lateinit var drawer: DrawerLayout

    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        setUpActionBarDrawer()

        setUpBottomNavigationView()

        addFragmentHome()
    }

    private fun setUpBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    // Respond to navigation item 1 click
                    true
                }

                R.id.page_2 -> {
                    // Respond to navigation item 2 click
                    true
                }

                R.id.page_3 -> {
                    true
                }

                R.id.page_4 -> {
                    true
                }

                R.id.page_5 -> {
                    true
                }

                else -> false
            }
        }
    }

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun observeViewModel() {
//        observe(recipesListViewModel.recipesLiveData, ::handleRecipesList)
//        observe(recipesListViewModel.recipeSearchFound, ::showSearchResult)
//        observe(recipesListViewModel.noSearchFound, ::noSearchResult)
//        observeEvent(recipesListViewModel.openRecipeDetails, ::navigateToDetailScreen)
//        observeSnackBarMessages(recipesListViewModel.showSnackBar)
//        observeToast(recipesListViewModel.showToast)
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val view = layoutInflater.inflate(R.layout.abs_layout, null, false)
            it.customView = view
        }
        imgHam = findViewById(R.id.tv_toolbar_open_drawer)
        drawer = findViewById(R.id.drawer_layout)
        container = findViewById(R.id.frame)
    }

    private fun setUpActionBarDrawer() {
        // Toggle Drawer
        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {

            override fun onDrawerOpened(drawerView: View) {
                drawerView.hideKeyboard()
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                drawer.setScrimColor(resources.getColor(android.R.color.transparent, null))
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                }
                super.onDrawerSlide(drawerView, slideOffset)
                container.translationX = slideOffset * drawerView.width
            }
        }
        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()

        // Event add back stack fragment
        supportFragmentManager.addOnBackStackChangedListener {
            container.hideKeyboard()
            hideLoading()
            if (supportFragmentManager.backStackEntryCount > 0) {
                imgHam.visibility = View.VISIBLE
                imgHam.setOnClickListener {
                    drawer.openDrawer(GravityCompat.START)
                }
                // show back button
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                // Animation back button
                ObjectAnimator.ofFloat(actionBarDrawerToggle.drawerArrowDrawable, "progress", 1f)
                    .start()
                actionBarDrawerToggle.syncState()
                toolbar.setNavigationOnClickListener { _: View? ->
                    onBackPressedDispatcher.onBackPressed()
                }
            } else {
                //show hamburger
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                imgHam.visibility = View.GONE
                // Animation hamburger button
                ObjectAnimator.ofFloat(actionBarDrawerToggle.drawerArrowDrawable, "progress", 0f)
                    .start()
                actionBarDrawerToggle.syncState()
                toolbar.setNavigationOnClickListener {
                    drawer.openDrawer(GravityCompat.START)
                }
            }
        }
    }

    private fun addFragmentHome() {
        val homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_content, homeFragment, HomeFragment.TAG)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu)
        return true
    }

}