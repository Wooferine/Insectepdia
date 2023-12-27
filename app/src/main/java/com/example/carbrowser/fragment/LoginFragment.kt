package com.example.carbrowser.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.carbrowser.AdminHomeScreen
import com.example.carbrowser.BackgroundWorkers
import com.example.carbrowser.R


class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {
    var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        // Set onclick for login
        view.findViewById<Button>(R.id.loginButton).setOnClickListener(this)
        return view
    }

    fun credentialsCheck(view: View): String{
        val user = view.findViewById<EditText>(R.id.userTextInput).text ?: ""
        val password = view.findViewById<EditText>(R.id.userPasswordInput).text ?: ""
        val type = "login"

        // Setup connection with php and mysql server
        var backgroundWorkers = BackgroundWorkers(view.context)

        // Credentials check and result read
        var result: String = backgroundWorkers.execute(user.toString(), password.toString(), type).get()
        return result
    }

    override fun onClick(v: View) {
        // Check credentials validity
        var result = credentialsCheck(requireActivity().window.decorView)

        // Access to admin activity if success
        if(result.equals("1")){

            toast?.cancel()
            toast = Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
            toast?.show()

            activity?.let {
                val intent = Intent(activity, AdminHomeScreen::class.java)
                startActivity(intent)
            }
        }else{
            toast?.cancel()
            toast = Toast.makeText(context, "Login Unsuccessful", Toast.LENGTH_LONG)
            toast?.show()
        }
    }
}