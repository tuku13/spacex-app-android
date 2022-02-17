package hu.tuku13.spacexapp.ui.launches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.network.Launch

class LaunchAdapter(
    private val onClickListener: (launch: Launch) -> Unit,
    private val successfulColor: Int,
    private val failureColor: Int,
    private val upcomingColor: Int
) : RecyclerView.Adapter<LaunchAdapter.ViewHolder>(){
    var launches : List<Launch> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.launch_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = launches[position]
        holder.item = item
        holder.name.text = item.name
        holder.description.text = item.date_utc

        Glide
            .with(holder.image)
            .load(item.links.patch.small)
            .into(holder.image)

        when {
            item.upcoming -> {
                holder.isSuccessful.text = "Upcoming"
                holder.isSuccessful.setTextColor(upcomingColor)
            }
            item.success == true -> {
                holder.isSuccessful.text = "Succesful"
                holder.isSuccessful.setTextColor(successfulColor)
            }
            else -> {
                holder.isSuccessful.text = "Failure"
                holder.isSuccessful.setTextColor(failureColor)
            }
        }

        holder.detailsIcon.setOnClickListener {
            onClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var item: Launch
        val name: TextView = itemView.findViewById(R.id.tvName)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
        val image: ImageView = itemView.findViewById(R.id.imgLogo)
        val detailsIcon : ImageView = itemView.findViewById(R.id.imgDetailsIcon)
        val isSuccessful: TextView = itemView.findViewById(R.id.tvSuccessful)
    }
}