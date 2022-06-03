/*  MainActivity.kt */

package com.example.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*setting onclicklistener for all our buttons and appending as text */
        zero.setOnClickListener{ appendOnExpression("0", true)}
        point.setOnClickListener{ appendOnExpression(".", true) }
        one.setOnClickListener{ appendOnExpression("1", true)}
        two.setOnClickListener{ appendOnExpression("2", true)}
        three.setOnClickListener{ appendOnExpression("3", true)}
        four.setOnClickListener{ appendOnExpression("4", true)}
        five.setOnClickListener{ appendOnExpression("5", true)}
        six.setOnClickListener{ appendOnExpression("6", true)}
        seven.setOnClickListener{ appendOnExpression("7", true)}
        eight.setOnClickListener { appendOnExpression("8", true)}
        nine.setOnClickListener{ appendOnExpression("9", true)}


        /* doing same with the operators but setting them as false*/
        add.setOnClickListener{ appendOnExpression("+", false)}
        subtract.setOnClickListener{ appendOnExpression("-", false)}
        multiply.setOnClickListener{ appendOnExpression("*", false)}
        divide.setOnClickListener{ appendOnExpression("/", false)}


        /* to clear everything, we are manually passing the enter and result button as empty*/
        clear.setOnClickListener{
            enter.text =""
            result.text=""
        }


        /*for backspace; to clear only the last character
        * once we get a value form a enter button then save it as  a string
        * if the string is not empty then calling a sub string to clear the last number */

        delbtn.setOnClickListener{
            val string =  enter.text.toString()
            if(string.isNotEmpty())
            {
                enter.text=string.substring(0, string.length-1)
            }
            result.text=""
        }


        /*for equal button using the ExpressionBuilder library to do the calculations
          using try and catch to pass through errors*/
        equal.setOnClickListener{
            try
            {
                val expression= ExpressionBuilder(enter.text.toString()).build()
                val display = expression.evaluate() //storing the value expression in a variable display
                val longDisplay=display.toLong() //and converting them to long
                if(display == longDisplay.toDouble()) //if num and longnum is same then
                {
                    result.text=longDisplay.toString() //passing the result as long
                }
                else
                {
                    result.text=display.toString() //else passing as double
                }
            }
            catch (e:Exception)
            {
                Log.d("Exception", "message:" +e.message)
            }
        }
    }


/*in order to append the each button for each click
  creating a function appendOnExpression with a string and
  boolean expression (for clearing value and retaining a value depending on a situation) */

    fun appendOnExpression(string: String, canClear:Boolean)
    {
        /*if the result button is not empty then clearing the result */

        if(result.text.isNotEmpty())
        { enter.text = "" }
       /* if canClear is true then passing empty result and appending  as text */
        if (canClear)
        {
            result.text = ""
            enter.append(string)
        }
        else
        {
            enter.append(result.text) /* appending the enter and result as text */
            enter.append(string)
            result.text= ""
        }

    }
}
