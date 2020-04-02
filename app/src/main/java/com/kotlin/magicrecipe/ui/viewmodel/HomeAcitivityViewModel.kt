package com.kotlin.magicrecipe.ui.viewmodel

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.kotlin.magicrecipe.R
import com.kotlin.magicrecipe.data.UserRepository
import com.kotlin.magicrecipe.ui.AuthListener
import com.kotlin.magicrecipe.ui.HomeActivity
import com.kotlin.magicrecipe.util.ApiException
import com.kotlin.magicrecipe.util.Coroutines
import com.kotlin.magicrecipe.util.isInternetAvailable
import com.kotlin.magicrecipe.util.show
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.StringBuilder

class HomeAcitivityViewModel :
    ViewModel() {

    var authListener: AuthListener? = null

    fun getRecipeList(context: HomeActivity) {
        if (isInternetAvailable(context)) {
            Coroutines.main {
                try {
                    val response = UserRepository().getRecipes()
                    response.results?.let {
                        authListener?.onSuccess(it)
                        return@main
                    }
                    authListener?.onFailure("Faliure")

                } catch (e: ApiException) {
                    authListener?.onFailure(e.message.toString())
                }
            }
        } else {
            authListener?.onFailure("No Internet, Please check connectivity")
        }

    }

    fun showDialog(homeActivity: HomeActivity) {
        lateinit var dialog: AlertDialog

        var list: StringBuilder = StringBuilder()

        var array: Array<out String> =
            homeActivity.getResources().getStringArray(R.array.ingredients);

        //This is just for demo purpose
        var arrayChecked = booleanArrayOf(
            false,false,false,false,false,false,false,false, false, false, false,false,false,
            false,false,false,false,false,false,false,false,false,false,false,false,false,
            false,false,false, false
        )

        val builder = AlertDialog.Builder(homeActivity)
        builder.setTitle("Choose Ingredients:")
        builder.setMultiChoiceItems(array, arrayChecked) { dialog, which, isChecked ->
            arrayChecked[which] = isChecked
            if (isChecked) {
                list = list.append(array[which] + ",")
            }
        }
        builder.setPositiveButton("OK") { _, _ ->
            for (i in 0 until array.size) {
                val checked = arrayChecked[i]
                if (checked) {
                    if (isInternetAvailable(homeActivity)) {
                        homeActivity.progressBar.show()
                        Coroutines.main {
                            try {
                                val response = UserRepository().getQueriedRecipe(list)
                                response.results?.let {
                                    authListener?.onSuccess(it)
                                    return@main
                                }
                                authListener?.onFailure("Faliure")

                            } catch (e: ApiException) {
                                authListener?.onFailure(e.message.toString())
                            }
                        }
                    } else {
                        authListener?.onFailure("No Internet, Please check connectivity")
                    }
                }
            }
        }

        dialog = builder.create()
        dialog.show()
    }

}
