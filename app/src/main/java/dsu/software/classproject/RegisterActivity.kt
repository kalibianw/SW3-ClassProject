package dsu.software.classproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var duplicateIdStatus = 0

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("Start Notification", "Start RegisterActivity")

        val registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {
            Log.d("Action Notification", "Register button clicked.")

            val userName = findViewById<EditText>(R.id.userName)
            val userBirthday = findViewById<EditText>(R.id.userBirthday)
            val userHome = findViewById<EditText>(R.id.userHome)
            val userId = findViewById<EditText>(R.id.userId)
            val userPw = findViewById<EditText>(R.id.userPw)

            val pref = getSharedPreferences("user_details", MODE_PRIVATE)
            val editor = pref.edit()
            val mainMenuActivityIntent = Intent(this, MainMenuActivity::class.java)

            if (userName.text.toString().isEmpty()) {
                Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
                userName.requestFocus()
                return@setOnClickListener
            }
            if (userBirthday.text.toString().isEmpty()) {
                Toast.makeText(this, "주민등록번호 앞자리를 입력하세요.", Toast.LENGTH_SHORT).show()
                userBirthday.requestFocus()
                return@setOnClickListener
            }
            if (userHome.text.toString().isEmpty()) {
                Toast.makeText(this, "거주지를 입력하세요.", Toast.LENGTH_SHORT).show()
                userHome.requestFocus()
                return@setOnClickListener
            }
            if (userId.text.toString().isEmpty()) {
                Toast.makeText(this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                userId.requestFocus()
                return@setOnClickListener
            }
            if (userPw.text.toString().isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                userPw.requestFocus()
                return@setOnClickListener
            }

            val addUser = AddUser(
                applicationContext,
                userName.text.toString(),
                userBirthday.text.toString(),
                userHome.text.toString(),
                userId.text.toString(),
                userPw.text.toString()
            )

            addUser.start()
            if (duplicateIdStatus == 1) {
                Toast.makeText(this, "아이디(전화번호)가 중복됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                editor.putString("userId", userId.text.toString())
                editor.putString("userPw", userPw.text.toString())
                editor.apply()
                startActivity(mainMenuActivityIntent)
            }
        }
    }
}

class AddUser(
    val context: Context,
    val userName: String,
    val userBirthday: String,
    val userHome: String,
    val userId: String,
    val userPw: String
) : Thread() {
    override fun run() {
        val alreadyRegisteredIdList = UserDatabase.getInstance(context)!!.getUserDao().getAllValues()
        for (alreadyRegisteredId in alreadyRegisteredIdList) {
            if (alreadyRegisteredId.userId == userId) {
                duplicateIdStatus = 1
                Log.d("Value", "ID duplicated.")
                return
            }
        }
        val user = UserEntity(userId, userPw, userName, userBirthday, userHome)
        UserDatabase.getInstance(context)!!.getUserDao().insert(user)
        duplicateIdStatus = 0
        Log.d("Value", "ID doesn't duplicate.")
    }
}