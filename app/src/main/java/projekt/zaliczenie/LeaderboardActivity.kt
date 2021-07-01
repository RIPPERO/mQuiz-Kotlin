package projekt.zaliczenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.onerow.*
import kotlin.collections.ArrayList

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var myRef1: DatabaseReference
    private lateinit var listOfItems: ArrayList<Leaderboard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Ustawienie widoków w formie listy
        recyclerView.layoutManager = LinearLayoutManager(this);

        val firebase = FirebaseDatabase.getInstance()
        myRef1 = firebase.getReference("ArrayData")

        myRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfItems = ArrayList()
                for (i in snapshot.children) {
                    val newRow = i.getValue(Leaderboard::class.java)
                    listOfItems.add(newRow!!)
                }
                listOfItems.sortByDescending { username -> username.score }
                setupAdapter(listOfItems)
            }

            override fun onCancelled(error: DatabaseError) {
                leader_username.text = "Błąd bazy danych"
            }
        })
    }

    private fun setupAdapter(arrayData: ArrayList<Leaderboard>) {
        recyclerView.adapter = Adapter(arrayData)
    }
}