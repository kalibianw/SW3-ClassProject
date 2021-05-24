package dsu.software.classproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Start Notification", "Start MainActivity")

        val informationInquiryActivityIntent = Intent(this, InformationInquiryActivity::class.java)
        val informationInquiryButton = findViewById<Button>(R.id.informationInquiryButton)
        informationInquiryButton.setOnClickListener {
            Log.d("Action Notification", "Information inquiry button clicked.")
            startActivity(informationInquiryActivityIntent)
        }
        
        val notificationSettingButton = findViewById<Button>(R.id.notificationSettingButton)
        notificationSettingButton.setOnClickListener {
            Log.d("Action Notification", "Notification setting button clicked.")
        }

        val myInformationButton = findViewById<Button>(R.id.myInformationButton)
        myInformationButton.setOnClickListener {
            Log.d("Action Notification", "My information button clicked.")
        }
    }
}