package com.toolivo.smarttools

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.security.SecureRandom
import java.time.LocalDate
import java.time.Period

class ToolActivity:AppCompatActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContentView(R.layout.activity_tool);val tool=intent.getStringExtra("tool")?:"Tool";val title=findViewById<TextView>(R.id.title);val input=findViewById<EditText>(R.id.input);val action=findViewById<Button>(R.id.action);val output=findViewById<TextView>(R.id.output);title.text=tool
 input.hint=when(tool){"Calculator"->"Example: 12+8 or 20*5";"Unit Converter"->"Example: km:5 or c:35";"Age Calculator"->"YYYY-MM-DD";"Password Generator"->"Password length, e.g. 16";"Tip Calculator"->"bill,tip% e.g. 2500,10";"BMI Calculator"->"weightKg,heightCm e.g. 70,175";else->"Enter text or value"}
 action.setOnClickListener{output.text=runTool(tool,input.text.toString().trim())}}
 private fun runTool(tool:String,value:String):String=try{when(tool){
 "Calculator"->calc(value);"Unit Converter"->convert(value);"Age Calculator"->{val p=Period.between(LocalDate.parse(value),LocalDate.now());"${p.years} years, ${p.months} months, ${p.days} days"};
 "Password Generator"->{val n=(value.toIntOrNull()?:16).coerceIn(6,64);val c="ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%&*";val r=SecureRandom();(1..n).joinToString(""){c[r.nextInt(c.length)].toString()}};
 "Text Counter"->{val w=value.split(Regex("\\s+")).filter{it.isNotBlank()}.size;"Characters: ${value.length}\nWords: $w"};"Text Cleaner"->value.replace(Regex("\\s+")," ").trim();
 "Tip Calculator"->{val p=value.split(",");val b=p[0].toDouble();val t=p[1].toDouble();val x=b*t/100;"Tip: %.2f\nTotal: %.2f".format(x,b+x)};
 "BMI Calculator"->{val p=value.split(",");val kg=p[0].toDouble();val m=p[1].toDouble()/100;"BMI: %.1f".format(kg/(m*m))};
 else->"This feature is being prepared for the next build."}}catch(e:Exception){"Please enter a valid value."}
 private fun calc(e:String):String{val s=e.replace(" ","");for(op in listOf("+","-","*","/")){val i=s.indexOf(op,1);if(i>0){val a=s.substring(0,i).toDouble();val b=s.substring(i+1).toDouble();val r=when(op){"+"->a+b;"-"->a-b;"*"->a*b;else->a/b};return if(r%1.0==0.0)r.toLong().toString() else r.toString()}};return "Use format like 12+8"}
 private fun convert(v:String):String{val p=v.split(":");if(p.size!=2)return "Use km:5, mi:3, c:35, f:95";val n=p[1].toDouble();return when(p[0].lowercase()){ "km"->"%.2f miles".format(n*.621371);"mi"->"%.2f km".format(n/.621371);"c"->"%.2f °F".format(n*9/5+32);"f"->"%.2f °C".format((n-32)*5/9);else->"Supported: km, mi, c, f"}}
}
