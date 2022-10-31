package com.greedy.eatopia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.greedy.eatopia.databinding.FragmentLoginBinding
import com.greedy.eatopia.databinding.FragmentUserBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.join.setOnClickListener {
            mainActivity.startActivity(Intent(mainActivity, JoinActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            mainActivity.startActivity(Intent(mainActivity, UserFragment::class.java))

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
//
//            /* 유효성 검사 추가할 수 있음 */
//            if(email.isNotEmpty() && password.isNotEmpty()) {
//                signIn(email, password)
//            } else {
////                Toast.makeText(this, "email과 password를 입력해주세요", Toast.LENGTH_SHORT).show()
//            }
        }

        return binding.root
    }

     //사용자가 앱에 로그인하면 사용자의 이메일 주소와 비밀번호를 signInWithEmailAndPassword에 전달
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) mainActivity = context
    }

    /*로그인된 상태라면 로그인 이후 화면으로 이동 */
     override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            moveResultPage()
        }
    }
//
    fun moveResultPage() {
        startActivity(Intent(mainActivity, UserFragment::class.java))
    }


}

