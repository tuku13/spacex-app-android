package hu.tuku13.spacexapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.tuku13.spacexapp.R

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    var images: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycleview_image_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.let {

            Glide
                .with(holder.imageView)
                .load(imageUrl)
                .into(holder.imageView)
        }
    }

    override fun getItemCount() = images.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgImage)
    }
}