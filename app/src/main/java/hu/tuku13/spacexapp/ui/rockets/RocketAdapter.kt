package hu.tuku13.spacexapp.ui.rockets

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.network.Rocket

class RocketAdapter(
    private val onClickListener: (rocket: Rocket) -> Unit
): RecyclerView.Adapter<RocketAdapter.ViewHolder>() {
    var rockets: List<Rocket> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rocket_recycle_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = rockets[position]
        holder.name.text = item.name
        Glide
            .with(holder.image)
            .load(item.flickr_images[0])
            .into(holder.image)

        holder.image.setOnClickListener{
            onClickListener(item)
        }
    }

    override fun getItemCount() = rockets.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvRocketName)
        val image: ImageView = itemView.findViewById(R.id.imgRocketImage)
    }
}