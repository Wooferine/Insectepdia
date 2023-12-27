package com.example.carbrowser
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.carbrowser.databinding.ActivitySplashBinding
import com.example.carbrowser.fragment.LoginFragment

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view using viewbinder feature
        val bind: ViewBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)
        val buttonStart = view.findViewById<Button>(R.id.startButton)
        val textLogin = view.findViewById<TextView>(R.id.login)
        buttonStart.setOnClickListener(this)
        textLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.startButton ->{
                val intent = Intent(v.context, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            R.id.login -> {
                // Casting animation on login fragment
                val transaction = supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(
                    R.anim.fadein,
                    R.anim.fadeout,
                    R.anim.fadein,
                    R.anim.fadeout,)
                transaction.replace(R.id.fragmentView, LoginFragment())
                transaction.addToBackStack("splashFragment")
                transaction.commit()
            }
        }
    }
}
