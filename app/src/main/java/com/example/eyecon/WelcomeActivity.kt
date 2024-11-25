package com.example.eyecon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eyecon.auth.LoginActivity
import com.example.eyecon.auth.RegistrasiActivity
import com.example.eyecon.databinding.ActivityLoginBinding
import com.example.eyecon.databinding.ActivityWelcomeBinding
import com.example.eyecon.ui.BoardingActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.daftar.setOnClickListener {
            val intent = Intent(this, RegistrasiActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }

        supportActionBar?.hide()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Pengguna sudah login, langsung buka MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // Menutup LoginActivity agar tidak bisa kembali
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        }
    }
}