package kg.geektech.customcaps.ui.fragments.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.data.models.ModelOrder
import kg.geektech.customcaps.databinding.ListOrdersBinding
import kg.geektech.customcaps.ui.fragments.orders.OrderAdapter.ViewHolder

class OrderAdapter(private var list: ArrayList<ModelOrder>) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var onItemClick: OnItemClick

    inner class ViewHolder(private val binding: ListOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(order: ModelOrder) {
            binding.tvId.text = order.id
            binding.tvDate.text = order.date
            binding.tvStatus.text = order.status
        }

        fun onItemClickListener(order: ModelOrder) {
            binding.root.setOnClickListener {
                onItemClick.onClick(order)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListOrdersBinding =
            ListOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.onItemClickListener(list[position])
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClick {
        fun onClick(order: ModelOrder)
    }
}