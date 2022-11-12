package uz.example.mvpproject.presenter

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.network.RetrofitHttp
import uz.example.mvpproject.presenter.impls.DetailPresentImpl
import uz.example.mvpproject.view.DetailView

class DetailPresenter(var detailView: DetailView):DetailPresentImpl {
    override fun apiLoadPost(id: Int) {
        RetrofitHttp.postService.detailPost(id)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                    if (response.body() != null) {
                        Log.d("@@@", response.body().toString())
                        detailView.onLoadSuccess(response.body()!!)

                    } else {
                        detailView.onLoadFailure(response.toString())
                        Log.d("@@@", response.toString())
                    }
                }

                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    detailView.onLoadFailure(t.message.toString())
                    Log.d("@@@", t.toString())
                }
            })
    }

}