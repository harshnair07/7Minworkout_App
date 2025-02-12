package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding?.root)
        //val flStartButton:FrameLayout=findViewById(R.id.flStart)
        binding?.flStart?.setOnClickListener{
            Toast.makeText(
                this@MainActivity,
                "Lets Start",
                Toast.LENGTH_SHORT
            ).show()

            val intent=Intent(this,ExcerciseActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        binding=null
    }
}