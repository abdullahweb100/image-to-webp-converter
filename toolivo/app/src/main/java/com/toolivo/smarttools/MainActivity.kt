package com.toolivo.smarttools

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private val tools = listOf("Calculator","Unit Converter","Age Calculator","Password Generator","Text Counter","Text Cleaner","QR Generator","Image Compressor","Image to PDF","PDF Merge","Document Scanner","Background Remover","Photo Resize","File Size Checker","Tip Calculator","BMI Calculator")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main)
        val grid=findViewById<GridLayout>(R.id.toolsGrid)
        tools.forEach { tool ->
            val card=MaterialCardView(this).apply { radius=24f; cardElevation=3f; setContentPadding(14,22,14,22); layoutParams=GridLayout.LayoutParams().apply { width=0; height=GridLayout.LayoutParams.WRAP_CONTENT; columnSpec=GridLayout.spec(GridLayout.UNDEFINED,1f); setMargins(8,8,8,8) } }
            val text=TextView(this).apply { this.text=tool; textSize=16f; gravity=Gravity.CENTER; setTextColor(getColor(R.color.toolivo_text)); setPadding(8,24,8,24) }
            card.addView(text); card.setOnClickListener { startActivity(Intent(this,ToolActivity::class.java).putExtra("tool",tool)) }; grid.addView(card)
        }
    }
}
