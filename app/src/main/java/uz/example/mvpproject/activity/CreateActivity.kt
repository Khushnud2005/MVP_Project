package uz.example.mvpproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import uz.example.mvpproject.R
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.presenter.CreatePresenter
import uz.example.mvpproject.utils.Utils.toast
import uz.example.mvpproject.view.CreateView

class CreateActivity : AppCompatActivity(),CreateView {

    lateinit var et_idUser: EditText
    lateinit var et_title: EditText
    lateinit var et_post: EditText
    lateinit var btn_create: Button

    lateinit var createPresenter: CreatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
    }

    private fun initViews() {
        et_idUser = findViewById(R.id.et_userIdCreate)
        et_title = findViewById(R.id.et_titleCreate)
        et_post = findViewById(R.id.et_textCreate)
        btn_create = findViewById(R.id.btn_submitCreate)

        createPresenter = CreatePresenter(this)

        btn_create.setOnClickListener(View.OnClickListener {
            val title: String = et_title.getText().toString()
            val body: String = et_post.getText().toString().trim { it <= ' ' }
            val id_user: String = et_idUser.getText().toString().trim { it <= ' ' }

            if (title.isNotEmpty() && body.isNotEmpty() && id_user.isNotEmpty()){
                val post = Post(id_user.toInt(), title, body)
                createPresenter.apiPostCreate(post)
            }

        })
    }

    override fun createPostSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        super@CreateActivity.onBackPressed()
    }

    override fun createPostFailure(error: String) {
        toast(this,error)
    }
}