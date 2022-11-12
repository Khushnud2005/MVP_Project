package uz.example.mvpproject.view

import uz.example.mvpproject.model.Post

interface EditView {
    fun onEditSuccess(post: Post)
    fun onEditFailure(error:String)
}