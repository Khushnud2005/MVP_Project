package uz.example.mvpproject.presenter

import android.util.Log
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.network.RetrofitHttp
import uz.example.mvpproject.presenter.impls.MainPresenterImpl
import uz.example.mvpproject.utils.Utils.toast
import uz.example.mvpproject.view.MainView

class MainPresenter(var mainView: MainView) :MainPresenterImpl {
    override fun apiPostList() {
        //pb_loading.visibility = View.VISIBLE
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                //Log.d("@@@", response.body().toString())
                /*pb_loading.visibility = View.GONE
                posts.clear()
                val items = response.body()
                if (items!=null){
                    for (item in items){
                        val post = Post(item.id,item.userId,item.title,item.body)
                        posts.add(post)
                    }
                }*/
                mainView.onPostListSuccess(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.e("@@@", t.message.toString())
                mainView.onPostListFailure(t.message.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                apiPostList()
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message.toString())
            }
        })
    }

}