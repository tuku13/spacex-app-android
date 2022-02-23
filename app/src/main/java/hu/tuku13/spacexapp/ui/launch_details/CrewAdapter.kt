package hu.tuku13.spacexapp.ui.launch_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.network.CrewMember

class CrewAdapter : RecyclerView.Adapter<CrewAdapter.ViewHolder>() {
    var crewMember: List<CrewMember> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.crew_member_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = crewMember[position]

        holder.let {
            it.name.text = member.name
            Glide
                .with(it.itemView)
                .load(member.image)
                .into(it.image)
        }
    }

    override fun getItemCount() = crewMember.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val image: ImageView = itemView.findViewById(R.id.imgCrewMemberPhoto)
    }
}