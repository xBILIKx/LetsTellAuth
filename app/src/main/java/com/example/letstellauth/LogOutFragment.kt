package com.example.letstellauth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.letstellauth.network.ApiClient
import com.example.letstellauth.network.LoginRequest
import com.example.letstellauth.network.LoginResponse
import retrofit2.Call
import retrofit2.Response


class LogOutFragment : Fragment() {
    private lateinit var apiClient: ApiClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_log_out, container, false)
    }

    override fun onStart() {
        super.onStart()

        view?.findViewById<Button>(R.id.logOutButton)?.setOnClickListener {

            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar2)

            progressBar?.visibility = ProgressBar.VISIBLE

            val email = arguments?.getString("email")
            val password = arguments?.getString("password")

            apiClient = ApiClient()
            apiClient.getLogOutService().login(LoginRequest(email!!, password!!))
                .enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {

                        progressBar?.visibility = ProgressBar.INVISIBLE
                        (activity as MainActivity).navController.navigate(R.id.action_logOutFragment_to_startFragment)
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        progressBar?.visibility = ProgressBar.INVISIBLE
                        Toast.makeText(this@LogOutFragment.context, "Pls, check you're internet connection", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}