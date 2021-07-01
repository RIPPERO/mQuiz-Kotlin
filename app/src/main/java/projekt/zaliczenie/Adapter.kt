package projekt.zaliczenie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataArray: ArrayList<Leaderboard>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.onerow, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.leaderUsername.text = dataArray[holder.adapterPosition].username
        holder.leaderScore.text = dataArray[holder.adapterPosition].score.toString()
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leaderUsername = view.findViewById(R.id.leader_username) as TextView
        val leaderScore = view.findViewById(R.id.leader_score) as TextView
    }
}