package org.d3if0108.piramidcount.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0108.piramidcount.R
import org.d3if0108.piramidcount.db.LimasEntity
import org.d3if0108.piramidcount.model.hitungLimas
import org.d3if0108.piramidcount.databinding.ItemHistoriBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<LimasEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LimasEntity>() {
                override fun areItemsTheSame(
                    oldData: LimasEntity, newData: LimasEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: LimasEntity, newData: LimasEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: LimasEntity) = with(binding) {
            val hasilLimas = item.hitungLimas()
          tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            limasTextView.text = root.context.getString(R.string.hasil_xx,
                hasilLimas.volumeLimas)
            }
        }

    }