package org.d3if0108.piramidcount.ui.penemu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0108.piramidcount.R
import org.d3if0108.piramidcount.databinding.ListItemBinding
import org.d3if0108.piramidcount.model.Penemu
import org.d3if0108.piramidcount.network.ServiceAPI


class PenemuAdapter : RecyclerView.Adapter<PenemuAdapter.ViewHolder>(){

    private val data = mutableListOf<Penemu>()

    fun updateData(newData: List<Penemu>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(penemu: Penemu) = with(binding){
            judulTextView.text = penemu.judul
            tglTextView.text = penemu.tgl
            Glide.with(imageView.context)
                .load(ServiceAPI.getPenemuUrl(penemu.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)
            tempatTextView.text = penemu.tempat
            deskripsiTextView.text = penemu.deskripsi


            root.setOnClickListener{
                val message = root.context.getString(R.string.message, penemu.judul)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}