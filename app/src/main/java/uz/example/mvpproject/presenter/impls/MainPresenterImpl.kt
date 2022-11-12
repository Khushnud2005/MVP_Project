package uz.example.mvpproject.presenter.impls

import uz.example.mvpproject.model.Post

interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
}