package uz.example.mvpproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.mvpproject.R
import uz.example.mvpproject.adapter.PostAdapter
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.network.RetrofitHttp
import uz.example.mvpproject.presenter.MainPresenter
import uz.example.mvpproject.utils.Utils
import uz.example.mvpproject.utils.Utils.toast
import uz.example.mvpproject.view.MainView

class MainActivity : AppCompatActivity(),MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var floating: FloatingActionButton

    lateinit var pb_loading: ProgressBar
    final val TAG = MainActivity::class.java.simpleName

    lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)
        pb_loading = findViewById(R.id.pb_loading)
        floating = findViewById(R.id.floating)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        floating.setOnClickListener { openCreateActivity() }

        mainPresenter.apiPostList()
        pb_loading.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            Log.d("@@@Extra",extras.getString("message")!!)
            toast(this, extras.getString("message")!!)
        }
    }



    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        recyclerView.setAdapter(adapter)
        pb_loading.visibility = View.GONE
    }
    fun openCreateActivity() {
        val intent = Intent(this@MainActivity, CreateActivity::class.java)
        launchCreateActivity.launch(intent)
    }

    var launchCreateActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            pb_loading.visibility = View.VISIBLE
            mainPresenter.apiPostList()
            Toast.makeText(this@MainActivity, "New Post Created", Toast.LENGTH_LONG).show()
            // your operation....
        } else {
            Toast.makeText(this@MainActivity, "Operation canceled", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {
        Log.d(TAG,error)
        pb_loading.visibility = View.GONE
    }

    override fun onPostDeleteSuccess(posts: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        pb_loading.visibility = View.GONE
    }


}