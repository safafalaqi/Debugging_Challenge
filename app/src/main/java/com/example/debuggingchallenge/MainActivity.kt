package com.example.debuggingchallenge


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private lateinit var listsRecyclerView: RecyclerView
    private lateinit var fabButton: FloatingActionButton
    private lateinit var alertDialogSubmitBtn: Button
    private lateinit var sharedPreferences: SharedPreferences

    private var arrayListOfCountriesAndCapitals = arrayListOf(
        arrayListOf("Saudi Arabia", "Riyadh"),
        arrayListOf("Nigeria", "Abuja"),
        arrayListOf("Rwanda", "Kigali"),
        arrayListOf("USA", "Washington"),
        arrayListOf("China", "Beijing"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        //if the shared preference list is not empty update the  list
        if(!getItemList("key").isEmpty())
            arrayListOfCountriesAndCapitals=getItemList("key")


        fabButton = findViewById(R.id.fabBtn)
        setupRecyclerView()

        fabButton.setOnClickListener {
            val singleUserEntryList = arrayListOf<String>()

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)




            alertDialogSubmitBtn.setOnClickListener {
                //Store user's input text to variables
                val countryText = countryET.text.toString()
                val capitalText = capitalET.text.toString()

                if (countryText.isEmpty() ) {
                  Toast.makeText(this,"Enter a country name ",Toast.LENGTH_LONG).show()


                } else if (capitalText.isEmpty()){
                    Toast.makeText(this,"Enter a capital city ",Toast.LENGTH_LONG).show()

                }
                    else{


                    //Add both texts to list
                    singleUserEntryList.add(countryText)
                    singleUserEntryList.add(capitalText)


                    //Add single entry list to Global list
                    arrayListOfCountriesAndCapitals.add(singleUserEntryList)
                    listsRecyclerView.adapter!!.notifyDataSetChanged()
                    listsRecyclerView.scrollToPosition(arrayListOfCountriesAndCapitals.size - 1)

                    alertDialog.dismiss()
                    setItemList("key",arrayListOfCountriesAndCapitals)
                }

            }
        }

    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)
        val builder =
            AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }

    private fun setupRecyclerView() {
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.adapter =
            ListSelectionRecyclerViewAdapter(arrayListOfCountriesAndCapitals,this)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    //this function is to set the shared preference recycler view list
    fun setItemList(key: String, ArrayList: ArrayList<ArrayList<String>>) {
        val arrayString = Gson().toJson(ArrayList)
        sharedPreferences.edit().putString(key, arrayString).apply()
    }
    //this function is to get the shared preference list
    fun getItemList(key: String): ArrayList<ArrayList<String>> {
        val emptyList = Gson().toJson(ArrayList<ArrayList<String>>())
        return Gson().fromJson(sharedPreferences.getString(key, emptyList), object :
            TypeToken<ArrayList<ArrayList<String>>>() {
        }.type)

    }

}