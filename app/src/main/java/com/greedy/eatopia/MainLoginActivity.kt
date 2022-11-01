package com.greedy.eatopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.eatopia.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityMainLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        /* 회원 가입 버튼 클릭 시 동작하는 이벤트 */
        binding.join.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        /* 로그인 버튼 클릭 시 동작하는 이벤트 */
        binding.btnLogin.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            /* 여러 유효성 검사 추가할 수 있음 */
            if(email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "email과 password를 입력해주세요", Toast.LENGTH_SHORT).show()
            }

        }

        }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                   // moveResultPage()
                } else {
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    /* 로그인 된 상태라면 로그인 이후 화면인 ResultActivity로 이동 */
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //moveResultPage()
        }
    }

    /* 로그인이 이미 되어 있는 상태 또는 로그인에 성공하는 상태에 호출할 메소드 */
//    fun moveResultPage() {
//        startActivity(Intent(this, ResultActivity::class.java))
//        finish()
//    }

    }
