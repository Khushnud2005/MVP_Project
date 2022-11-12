package uz.example.mvpproject.view

import uz.example.mvpproject.model.Post

interface CreateView {
    fun createPostSuccess(post: Post?)
    fun createPostFailure(error: String)
}