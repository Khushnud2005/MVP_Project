package uz.example.mvpproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import uz.example.mvpproject.R
import uz.example.mvpproject.model.Post
import uz.example.mvpproject.presenter.EditPresenter
import uz.example.mvpproject.view.EditView

class EditActivity : AppCompatActivity(),EditView {
    lateinit var et_idUser: EditText
    lateinit var et_title: EditText
    lateinit var et_post: EditText
    lateinit var btn_edit: Button
    lateinit var id: String

    lateinit var editPresenter: EditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initViews()
    }
    fun initViews(){
        val extras = intent.extras
        et_idUser = findViewById(R.id.et_userIdEdit)
        et_title = findViewById(R.id.et_titleEdit)
        et_post = findViewById(R.id.et_textEdit)
        btn_edit = findViewById(R.id.btn_submitEdit)

        editPresenter = EditPresenter(this)

        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            et_idUser.setText(extras.getString("user_id"))
            et_title.setText(extras.getString("title"))
            et_post.setText(extras.getString("body"))
            id = extras.getString("id")!!
        }
        btn_edit.setOnClickListener {
            val title = et_title.text.toString()
            val body = et_post.text.toString().trim { it <= ' ' }
            val id_user = et_idUser.text.toString().trim { it <= ' ' }
            if (title.isNotEmpty() && body.isNotEmpty() && id_user.isNotEmpty()){
                val post = Post(id.toInt(),id_user.toInt(), title, body)
                editPresenter.apiEditPost(post)
            }


        }
    }

    override fun onEditSuccess(post: Post) {
        val intent = Intent(this@EditActivity, MainActivity::class.java)
        intent.putExtra("message", post.title + " Edited")
        startActivity(intent)
    }

    override fun onEditFailure(error: String) {
        val intent = Intent(this@EditActivity, MainActivity::class.java)
        intent.putExtra("message", "Post Not Edited : $error")
        startActivity(intent)
    }
}