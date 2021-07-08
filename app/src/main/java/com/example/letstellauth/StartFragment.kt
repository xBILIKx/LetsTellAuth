package com.example.letstellauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.letstellauth.network.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Response

class StartFragment : Fragment() {

    private lateinit var apiClient: ApiClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onStart() {
        super.onStart()

        view?.findViewById<Button>(R.id.signInButton)?.setOnClickListener{ _ ->

            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

            progressBar?.visibility = ProgressBar.VISIBLE

            val email = view?.findViewById<EditText>(R.id.editEmail)!!.text.toString()
            val password = view?.findViewById<EditText>(R.id.editPassword)!!.text.toString()

            apiClient = ApiClient()
            apiClient.getAuthService().login("ru", LoginRequest(email, password))
                .enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val bundle = Bundle()
                        bundle.putString("email", email)
                        bundle.putString("password", password)

                        val loginResponse = response.body()

                        if (loginResponse == null) {
                            Toast.makeText(this@StartFragment.context, "This acc doesnt exist", Toast.LENGTH_LONG).show()
                            progressBar?.visibility = ProgressBar.INVISIBLE
                            return
                        }

                        progressBar?.visibility = ProgressBar.INVISIBLE
                        (activity as MainActivity).navController.navigate(R.id.action_startFragment_to_logOutFragment, bundle)
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        progressBar?.visibility = ProgressBar.INVISIBLE
                        Toast.makeText(this@StartFragment.context, "Pls, check you're internet connection", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}