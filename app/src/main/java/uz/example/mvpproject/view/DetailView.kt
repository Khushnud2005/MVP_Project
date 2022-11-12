package uz.example.mvpproject.view

import uz.example.mvpproject.model.Post

interface DetailView {
    fun onLoadSuccess(post: Post?)
    fun onLoadFailure(error: String)
}