package com.example.pruebatecnicamacm


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebatecnicamacm.data.model.Result
import com.example.pruebatecnicamacm.databinding.ViewDataItemBinding

interface DataClickedListener {
    fun onDataClicked(data: Result)
}

class DataAdapter(
    private val data: List<Result>,
    private val dataClickedListener: DataClickedListener )
    : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ViewDataItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: Result){
                binding.titleD.text = data.name
            Glide.with(binding.root.context).load(data.image).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ViewDataItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            dataClickedListener.onDataClicked(data)

        }
    }
}