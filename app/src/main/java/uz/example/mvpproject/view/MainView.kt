package uz.example.mvpproject.view

import uz.example.mvpproject.model.Post

interface MainView {

    fun onPostListSuccess(posts:ArrayList<Post>?)
    fun onPostListFailure(error:String)

    fun onPostDeleteSuccess(posts:Post?)
    fun onPostDeleteFailure(error:String)
}