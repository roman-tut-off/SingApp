package com.example.singapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.singapp.constance.Constance
import com.example.singapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null

    private var Login: String = "e"
    private var Password: String = "e"
    private var Name: String = "e"
    private var Avatar_id: Int = 0
    private var Family: String = "e"
    private var Email: String = "e"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val buttonIn = findViewById<Button>(R.id.button)
        val buttonUp = findViewById<Button>(R.id.button2)

        buttonIn.setOnClickListener(this::OnClickSingIn)
        buttonUp.setOnClickListener(this::OnClickSingUp)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            result: ActivityResult ->
            var signState = "s"
            if (result.resultCode == RESULT_OK)
            {
                signState = result.data?.getStringExtra(Constance.SIGN_STATE)!!
                if(signState == Constance.SIGN_IN_STATE)
                {
                    val lgn = result.data?.getStringExtra(Constance.LOGIN)
                    val psw = result.data?.getStringExtra(Constance.Password)
                    bind.imageView2.visibility = View.VISIBLE
                    if(Login == lgn && Password == psw)
                    {
                        bind.imageView2.setImageResource(Avatar_id)
                        bind.textView.text = "$Name $Family $Email"
                    }
                    else
                    {
                        bind.imageView2.setImageResource(Avatar_id)
                        bind.textView.text = "Invalid account"
                    }
                }
                else if (signState == Constance.SIGN_UP_STATE)
                {
                    Login = result.data?.getStringExtra(Constance.LOGIN)!!
                    Password = result.data?.getStringExtra(Constance.Password)!!
                    Name = result.data?.getStringExtra(Constance.NAME)!!
                    Email = result.data?.getStringExtra(Constance.EMAIL)!!
                    Family = result.data?.getStringExtra(Constance.FAMILY)!!
                    Avatar_id = result.data?.getIntExtra(Constance.AVATAR_ID, 0)!!
                    bind.textView.text = "HELLO"
                }

            }
        }
    }

    fun OnClickSingIn(view: View)
    {
        val intent = Intent(this, SingInUp::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        //startActivity(intent)
        launcher?.launch(intent)
    }

    fun OnClickSingUp(view: View)
    {
        val intent = Intent(this, SingInUp::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        launcher?.launch(intent)
    }
}


