package uz.example.mvpproject.presenter.impls

import uz.example.mvpproject.model.Post

interface DetailPresentImpl {
    fun apiLoadPost(id: Int)
}