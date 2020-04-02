package com.kotlin.magicrecipe.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.magicrecipe.R
import com.kotlin.magicrecipe.data.model.Recipes
import kotlinx.android.synthetic.main.recipe_lits_item.view.*

class RecipeListRvAdapter(private val context: Context) :

    RecyclerView.Adapter<RecipeListRvAdapter.RecipeViewHolder>() {

    private var listOfRecipes = listOf<Recipes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recipe_lits_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfRecipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.dish_title.text = listOfRecipes[position].title
        Glide.with(context).load(listOfRecipes[position].thumbnail).apply(
            RequestOptions()
                .placeholder(R.mipmap.bg_recipe)
        ).into(holder.dish_img)

        holder.dish_img.setOnClickListener {
            holder.webview.visibility = View.VISIBLE
            holder.webview.loadUrl(listOfRecipes[position].href)
        }

    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dish_img = view.dish_img!!
        val dish_title = view.dish_title!!
        val href_title = view.href_title!!
        val webview = view.webview!!
    }

    fun setRecipeList(listOfRecipes: List<Recipes>) {
        this.listOfRecipes = listOfRecipes
        notifyDataSetChanged()
    }
}

