package com.kotlin.magicrecipe.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.magicrecipe.R
import com.kotlin.magicrecipe.data.model.Recipes
import com.kotlin.magicrecipe.databinding.ActivityHomeBinding
import com.kotlin.magicrecipe.ui.adapter.RecipeListRvAdapter
import com.kotlin.magicrecipe.ui.viewmodel.HomeAcitivityViewModel
import com.kotlin.magicrecipe.util.hide
import com.kotlin.magicrecipe.util.show
import com.kotlin.magicrecipe.util.toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), AuthListener {

    private lateinit var viewModel: HomeAcitivityViewModel
    private lateinit var recipeListRvAdapter: RecipeListRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this).get(HomeAcitivityViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        loadData(viewModel)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun loadData(viewModel: HomeAcitivityViewModel) {
        viewModel.getRecipeList(this)
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(recipes: List<Recipes>) {
        no_connectivity.visibility = View.GONE
        recipeListRvAdapter = RecipeListRvAdapter(this)
        rv_recipe.layoutManager = GridLayoutManager(this, 2)
        rv_recipe.adapter = recipeListRvAdapter

        recipeListRvAdapter.setRecipeList(recipes)

        progressBar.hide()
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        toast(message)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.select_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.select_item -> {
                viewModel.showDialog(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
