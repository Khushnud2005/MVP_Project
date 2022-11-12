package uz.example.mvpproject.presenter

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.network.RetrofitHttp
import uz.example.mvpproject.presenter.impls.CreatePresenterImpl
import uz.example.mvpproject.view.CreateView

class CreatePresenter(var createView:CreateView):CreatePresenterImpl {
    override fun apiPostCreate(post: Post?) {
        RetrofitHttp.postService.createPost(post!!).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                createView.createPostSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                createView.createPostFailure(t.message.toString())
            }
        })
    }

}