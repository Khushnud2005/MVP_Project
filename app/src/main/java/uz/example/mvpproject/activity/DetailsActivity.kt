package uz.example.mvpproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import uz.example.mvpproject.R
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.presenter.DetailPresenter
import uz.example.mvpproject.utils.Utils.toast
import uz.example.mvpproject.view.DetailView

class DetailsActivity : AppCompatActivity(),DetailView {
    lateinit var tv_title: TextView
    lateinit var tv_body: TextView
    var postId:Int = 0

    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
    }

    private fun initViews() {
        tv_title = findViewById(R.id.tv_title_detail)
        tv_body = findViewById(R.id.tv_body_detail)

        detailPresenter = DetailPresenter(this)
        val extras = intent.extras
        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            postId = extras.getInt("id")
            detailPresenter.apiLoadPost(postId)
        }
    }

    override fun onLoadSuccess(post: Post?) {
        tv_title.setText(post!!.title.uppercase())
        tv_body.setText(post.body)
    }

    override fun onLoadFailure(error: String) {
        toast(this,error)
    }
}