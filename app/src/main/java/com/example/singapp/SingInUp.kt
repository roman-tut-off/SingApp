package com.example.singapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.singapp.constance.Constance
import com.example.singapp.databinding.ActivitySingInUpBinding

class SingInUp : AppCompatActivity() {
    lateinit var bind: ActivitySingInUpBinding
    public var signState = "s"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingInUpBinding.inflate(layoutInflater)
        setContentView(bind.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        Log.d("Applog", "Sign State: $signState")
        if(signState == Constance.SIGN_UP_STATE)
        {
            bind.editTextTextPersonName2.visibility = View.VISIBLE
            bind.editTextTextPersonName10.visibility = View.VISIBLE
            bind.editTextTextPersonName7.visibility = View.VISIBLE
            bind.editTextTextPersonName9.visibility = View.VISIBLE
            bind.editTextTextPersonName8.visibility = View.VISIBLE
            bind.button3.visibility = View.VISIBLE
        }
    }

    fun onClickAvatar(view: View)
    {
        bind.imageView.visibility = View.VISIBLE
    }

    fun onClickDone(view: View)
    {
        if(signState == Constance.SIGN_UP_STATE)
        {
            val i = Intent()
            i.putExtra(Constance.LOGIN, bind.editTextTextPersonName2.toString())
            i.putExtra(Constance.FAMILY, bind.editTextTextPersonName10.toString())
            i.putExtra(Constance.NAME, bind.editTextTextPersonName8.toString())
            i.putExtra(Constance.AVATAR_ID, bind.editTextTextPersonName9.toString())
            i.putExtra(Constance.Password, bind.editTextTextPersonName7.toString())
            i.putExtra(Constance.AVATAR_ID, R.drawable._23)
            i.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
            setResult(RESULT_OK, i)

            finish()
        }
        else if (signState == Constance.SIGN_IN_STATE)
        {
            intent.putExtra(Constance.LOGIN, bind.editTextTextPersonName2.toString())
            intent.putExtra(Constance.Password, bind.editTextTextPersonName7.toString())
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            finish()
        }
    }
}