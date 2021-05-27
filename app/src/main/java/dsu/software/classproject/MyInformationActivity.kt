package dsu.software.classproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MyInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        val defaultInformationEditButton = findViewById<Button>(R.id.defaultInformationEditButton)
        val editPasswordButton = findViewById<Button>(R.id.editPasswordButton)
        val serviceCenterButton = findViewById<Button>(R.id.serviceCenterButton)

        defaultInformationEditButton.setOnClickListener {
            Log.d("Action Notification", "Default information edit button clicked.")
        }
        editPasswordButton.setOnClickListener {
            Log.d("Action Notification", "Edit password button clicked.")
        }
        serviceCenterButton.setOnClickListener {
            Log.d("Action Notification", "Service center button clicked.")
        }
    }
}