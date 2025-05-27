package com.example.lazycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var currentValue = 0
    private var appendValue = ""
    private var currentOperator = ""

    private var value1 = 0
    private var value2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //updateDisplay()
        updateDisplayAfterOp("")
    }

    fun updateDisplay() {
        val outputView = findViewById<TextView>(R.id.numberDisplay)
        outputView.text = currentValue.toString()
    }

    fun updateDisplayAfterOp(displayValue: String) {
        val outputViewTop = findViewById<TextView>(R.id.operatorApplied)
        outputViewTop.text = displayValue

        val outputViewBot = findViewById<TextView>(R.id.numberDisplay)
        outputViewBot.text = currentValue.toString()
    }

    fun clearPressed(view: View) {
        currentValue = 0
        appendValue = ""

        currentOperator = ""

        value1 = 0
        value2 = 0

        //updateDisplay()
        updateDisplayAfterOp(appendValue)
    }

    fun numberButtonPressed(view: View) {
        val button = view as Button
        val digit = button.text.toString().toInt()
        currentValue *= 10
        currentValue += digit
        updateDisplay()

        /* for floating number
        have a boolean variable that checks whether if . was pressed so that
        it can track on whether if user tries to calculate floating number or not.
        This can also be used to check whether if user pressed . once or more.
        If it's latter, print out error message.

        Once the boolean variable determines that the user tries to calculate
        floating number, convert the 'digit' variable into float using something like toFloat().

        The rest can be brainstormed later.
        */
    }

    //https://stackoverflow.com/questions/51235401/convert-from-char-to-operator-kotlin
    fun operatorButtonPressed(view: View) {
        if (currentOperator != "") {
            value2 = currentValue

            if (currentOperator == "+") {
                currentValue = value1 + value2
            }

            else if (currentOperator == "-") {
                currentValue = value1 - value2
            }

            else if (currentOperator == "*") {
                currentValue = value1 * value2
            }

            else if (currentOperator == "/") {
                currentValue = value1 / value2
            }
        }

        val button = view as Button
        val operator = button.text.toString()

        value1 = currentValue
        currentOperator = operator

        appendValue = currentValue.toString() + operator
        currentValue = 0
        updateDisplayAfterOp(appendValue)
    }

    fun equalButtonPressed(view: View) {
        if (currentOperator != "") {
            value2 = currentValue
        }

        if (currentOperator == "+") {
            currentValue = value1 + value2
            currentOperator = ""
            value1 = 0
            value2 = 0
            updateDisplayAfterOp("")
        }

        else if (currentOperator == "-") {
            currentValue = value1 - value2
            currentOperator = ""
            value1 = 0
            value2 = 0
            updateDisplayAfterOp("")
        }

        else if (currentOperator == "*") {
            currentValue = value1 * value2
            currentOperator = ""
            value1 = 0
            value2 = 0
            updateDisplayAfterOp("")
        }

        else if (currentOperator == "/") {
            //if user attempts to divide the number by 0
            if (value2 == 0) {

                currentOperator = ""
                value1 = 0
                value2 = 0
                updateDisplayAfterOp("Undefined. Please press AC")
            }

            //if user attempt to divide the number by other normal value
            else {
                currentValue = value1 / value2
                currentOperator = ""
                value1 = 0
                value2 = 0
                updateDisplayAfterOp("")
            }
        }
    }
}