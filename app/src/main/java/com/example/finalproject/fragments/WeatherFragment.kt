package com.example.finalproject.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment: Fragment(R.layout.fragment_two) {

    val API: String = "c5be5db31ee374afcea72a6947132f60"


    private lateinit var mainContainer: RelativeLayout
    private lateinit var editTextCityName: EditText
    private lateinit var buttonSearch: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainContainer = view.findViewById(R.id.mainContainer)
        editTextCityName = view.findViewById(R.id.editTextCityName)
        buttonSearch = view.findViewById(R.id.buttonSearch)

        buttonSearch.setOnClickListener{
            weatherTask().execute()
            editTextCityName.visibility = View.GONE
            buttonSearch.visibility = View.GONE
            hideKeyBoard()
        }


    }
    private fun hideKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            view?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.VISIBLE
            view?.findViewById<RelativeLayout>(R.id.mainContainer)?.visibility = View.GONE
            view?.findViewById<TextView>(R.id.errorText)?.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                val CITY = editTextCityName.text.trim().toString()
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(

                    Charsets.UTF_8
                )

            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val windDegree = wind.getString("deg")+"°"
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                view?.findViewById<TextView>(R.id.address)?.text = address
                view?.findViewById<TextView>(R.id.updated_at)?.text =  updatedAtText
                view?.findViewById<TextView>(R.id.status)?.text = weatherDescription.capitalize()
                view?.findViewById<TextView>(R.id.temp)?.text = temp
                view?.findViewById<TextView>(R.id.temp_min)?.text = tempMin
                view?.findViewById<TextView>(R.id.temp_max)?.text = tempMax
                view?.findViewById<TextView>(R.id.sunrise)?.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                view?.findViewById<TextView>(R.id.sunset)?.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                view?.findViewById<TextView>(R.id.wind)?.text = windSpeed
                view?.findViewById<TextView>(R.id.about)?.text = windDegree
                view?.findViewById<TextView>(R.id.pressure)?.text = pressure
                view?.findViewById<TextView>(R.id.humidity)?.text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                view?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.GONE
                view?.findViewById<RelativeLayout>(R.id.mainContainer)?.visibility = View.VISIBLE

            } catch (e: Exception) {
                view?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.GONE
                view?.findViewById<TextView>(R.id.errorText)?.visibility = View.VISIBLE
            }

        }
    }
}