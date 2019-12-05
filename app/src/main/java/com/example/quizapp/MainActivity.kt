package com.example.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    var score1 = 0
    var score2 = 0
    var points = 0

    @RequiresApi(Build.VERSION_CODES.O)
    var date: LocalDateTime = LocalDateTime.now()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            val radioGroup = radio_group.findViewById(checkedId) as RadioButton
            val checked = radioGroup.isChecked
            if(checked && radioGroup.text.toString() == ".dex"){
                score1 += 50
            }else if(checked && radioGroup.text.toString() != ".dex" && score1>0){
                score1 -=50
            }
        }

        pub.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) score2 += 50
        }

        prot.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked && score2 > 0) score2 -= 50

        }

        priv.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked && score2 > 0) score2 -= 50
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submit(view: View){

        points = score1 + score2
        var alertResult = AlertDialog.Builder(this)
        alertResult.setTitle("Your Result")
        val formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        var formatedTime = date.format(formatter)
        alertResult.setMessage("Congratulations! You submitted on $formatedTime, You achieved $points%")
        val alert: AlertDialog = alertResult.create()
        alert.show()
        clear()
    }

    fun clear(){
        score1 = 0
        score2 = 0
        points = 0
        pub.isChecked=false
        priv.isChecked = false
        prot.isChecked = false
        rb1.isChecked = false
        rb2.isChecked = false
        rb3.isChecked = false

    }

    fun reset(view : View){
        clear()
    }

}
