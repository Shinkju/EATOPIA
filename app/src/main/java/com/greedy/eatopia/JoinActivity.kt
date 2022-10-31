package com.greedy.eatopia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.eatopia.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // Initialize Firebase Auth
        auth = Firebase.auth

        // 가입 버튼 클릭 시 firebase로 계정 등록 요청
        binding.btnJoin.setOnClickListener{
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val pwd = binding.password.text.toString()
            val birth = binding.birth.text.toString()
            val gender = binding.gender.text.toString()

            /* 여러 유효성 검사 추가할 수 있음  */
            if(email.isNotEmpty() && pwd.isNotEmpty()){
                createAccount( name, email, pwd, birth, gender)
            } else {
                Toast.makeText(this, "id와 password를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }


        // 취소 버튼 클릭 시  JoinActivity 종료 (MainActivity로 돌아감)
        binding.btnCancle.setOnClickListener { finish()  }


    }

    private fun createAccount( name: String, email: String, pwd: String, birth: String, gender: String) {

        auth.createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //회원 가입 성공 시
                    Toast.makeText(this, "회원 가입 완료", Toast.LENGTH_SHORT).show()
                    finish() //회원 가입창 종료하고 메인 - 로그인창으로 돌아감
                } else {
                   //회원 가입 실패 시
                    Toast.makeText(this, "회원 가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인 후 회원 가입 창 액티비티 종료
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

        val currentUser = auth.currentUser
        if(currentUser != null){
            finish()
        }
    }
}