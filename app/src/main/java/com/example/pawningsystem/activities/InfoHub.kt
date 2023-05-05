package com.example.pawningsystem.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.pawningsystem.R


class InfoHub : AppCompatActivity() {

    private lateinit var btnGoogle: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_hub)

        btnGoogle = findViewById(R.id.google)

        btnGoogle.setOnClickListener {
            goToUrl("https://www.sliit.lk");
        }
    }

    private fun goToUrl(website: String) {
        val uri = Uri.parse(website)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}