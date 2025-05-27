package com.example.myastronomypic

import android.content.Context
import android.icu.util.Calendar
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class APoDFetcher(c: Context, l: APoDListener) {
    val queue : RequestQueue
    val listener: APoDListener
    //var dataLoaded: Boolean = true

    init {
        queue = Volley.newRequestQueue(c)
        listener = l
    }

    /*
    ApoD & Volley related Documents:
    https://github.com/nasa/apod-api
    https://google.github.io/volley/request.html
    https://google.github.io/volley/requestqueue.html

    Resources:
    https://stackoverflow.com/questions/33361713/how-to-get-particular-values-from-volley-json-response-data-using-android
    https://stackoverflow.com/questions/5946847/edittext-to-string
    https://stackoverflow.com/questions/5369682/how-to-get-current-time-and-date-in-android
    https://developer.android.com/reference/java/util/Date#getYear()
    https://developer.android.com/reference/java/util/Calendar#YEAR
    https://www.geeksforgeeks.org/date-and-time-formatting-in-android/#
    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.js/-date/

    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/digit-to-int.html
    https://kotlinlang.org/docs/typecasts.html

    https://www.baeldung.com/kotlin/current-date-time
    https://www.baeldung.com/kotlin/string-to-int
    */

    //image = "hdurl"

    fun doRequest(dateInput: EditText) {
        var dateValid: Boolean = true

        val date = dateInput.getText().toString()

        //check if given date has valid format

        if (date.length == 10) {
            for (i in date.indices) {
                if (i == 4 || i == 7) {
                    if (date[i] != '-') {
                        dateValid = false
                    }
                } else {
                    if (!(date[i].digitToInt() == 1 || date[i].digitToInt() == 2 || date[i].digitToInt() == 3 || date[i].digitToInt() == 4 || date[i].digitToInt() == 5 || date[i].digitToInt() == 6 || date[i].digitToInt() == 7 || date[i].digitToInt() == 8 || date[i].digitToInt() == 9 || date[i].digitToInt() == 0)) {
                        dateValid = false
                    }
                }
            }
        }

        else {
            dateValid = false
        }

        //given date has wrong format
        if (!dateValid) {
            /*
            val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + date
            //val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"

            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val result = APoDError(
                        response.getInt("code"),
                        response.getString("msg"),
                    )
                    listener.processAPoDError(result)
                },
                { print("Failed") }
            )

            queue.add(request)
            */
            val result = APoDError("Invalid date format")

            listener.processAPoDError(result)
        }

        else {
            //check if given date has valid range. If it's a future, then set it to false
            var rangeValid: Boolean = true

            //check if first digit of the year is neither 1 or 2
            //if yes, then the year is invalid right away
            if (date[0].digitToInt() < 1 || date[0].digitToInt() > 2) {
                rangeValid = false
            }

            val year = date[0] + "" + date[1] + "" + date[2] + "" + date[3]

            var month = date[6].toString()

            //check if month is either October(10), November(11), or December(12)
            //if not, just let 'month' variable have single digit that indicates corresponding month
            if (date[5].digitToInt() != 0) {
                month = date[5] + "" + date[6]
            }

            var day = date[9].toString()

            //check if day is a double digit day
            //if not, just let 'day' variable have single digit that represents that specific day
            if (date[8].digitToInt() != 0) {
                day = date[8] + "" + date[9]
            }

            //.digitToInt()

            val calendarData = Calendar.getInstance()
            val currentYear = calendarData.get(Calendar.YEAR)
            val currentMonth = calendarData.get(Calendar.MONTH) + 1 //must add 1 since January is value 0
            val currentDay = calendarData.get(Calendar.DAY_OF_MONTH)


            //if given year is out of valid range, then don't even bother validating any further
            //if (!rangeValid) {
            //    loadFail()
            //}

            //if given year is within valid range, check if month and day are valid as well
            //which may vary depending on given year
            //else {
            //first, check the valid range of years
            if (year.toInt() >= 1995 && year.toInt() <= currentYear) {
            //next, check if year is either 1995, currentYear, or any years between them

                //if it's a year 1995
                if (year.toInt() == 1995) {

                //if yes, check if month is in between June(6) or December(12)
                    if (month.toInt() >= 6 && month.toInt() <= 12) {
                        //now check if day is in between 16th or 31st
                        //if not, then given date is out of valid range
                        if (day.toInt() < 16 || day.toInt() > 31) {
                            rangeValid = false
                        }
                    }

                    //given date is out of valid range
                    else {
                        rangeValid = false
                    }
                }

                //if it's current year
                else if (year.toInt() == currentYear) {
                    if (month.toInt() >= 1 && month.toInt() <= currentMonth) {
                        //given date is out of valid range
                        if (day.toInt() < 1 || day.toInt() > currentDay) {
                            rangeValid = false
                        }
                    }

                    //given date is out of valid range
                    else {
                        rangeValid = false
                    }
                }

                //if the given year is between 1995 and current year
                else {
                    if (month.toInt() >= 1 && month.toInt() <= 12) {
                        //given date is out of valid range
                        if (day.toInt() < 1 || day.toInt() > 31) {
                            rangeValid = false
                        }
                    }

                    //given date is out of valid range
                    else {
                        rangeValid = false
                    }
                }
            }

            //given date is out of valid range
            else {
                    rangeValid = false
            }


            //if given date is entirely valid, process the request
            if (rangeValid) {
                val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + date
                //val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"

                val request = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    { response ->
                        val result = APoDResult(
                            response.getString("title"),
                            response.getString("copyright"),
                            response.getString("explanation"),
                            response.getString("hdurl")
                        )
                        listener.processAPoDResult(result)
                    },
                    { print("Failed") }
                )

                queue.add(request)
            }

            //if not, provide a message saying there's no valid date
            else {
                /*
                val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + date
                //val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"

                val request = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    { response ->
                        val result = APoDError(
                            response.getInt("code"),
                            response.getString("msg"),
                        )
                        listener.processAPoDError(result)
                    },
                    { print("Failed") }
                )

                queue.add(request)
                */

                val result = APoDError("Given date has invalid range")

                listener.processAPoDError(result)
            }
        }
    }
}
