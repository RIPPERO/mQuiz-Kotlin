package projekt.zaliczenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*

class ResultActivity : AppCompatActivity() {

    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val firebase = FirebaseDatabase.getInstance()
        myRef = firebase.getReference("ArrayData")

        val usernameDisplayed = intent.getStringExtra(Constants.username).toString()
        username_result.text = "Gratulacje $usernameDisplayed!"
        val maxQuestions = intent.getIntExtra(Constants.maxQuestions, 0)
        val correctAnswers = intent.getIntExtra(Constants.correctAnswers, 0)

        //Dodanie wyniku do bazy
        val firebaseInput = Leaderboard(usernameDisplayed, correctAnswers)
        myRef.child("${Date().time}").setValue(firebaseInput)

        if (correctAnswers > (maxQuestions / 2)) {
            username_result.text = "Gratulacje $usernameDisplayed!"
            score.text = "Zdobyłeś $correctAnswers na $maxQuestions punktów!"
        } else {
            username_result.text = "Niestety $usernameDisplayed. Musisz jeszcze poćwiczyć"
            score.text = "Zdobyłeś $correctAnswers na $maxQuestions punktów!"
        }

        btn_leader.setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}