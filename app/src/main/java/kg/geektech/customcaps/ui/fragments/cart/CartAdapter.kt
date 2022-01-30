package kg.geektech.customcaps.ui.fragments.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.models.ModelCap
import kg.geektech.customcaps.databinding.ListCartBinding

class CartAdapter(private val caps: MutableList<ModelCap>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListCartBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        caps[position].img?.let { holder.img.setImageResource(it) }
        holder.brand.text = caps[position].brand
        holder.model.text = caps[position].model
        holder.price.text = caps[position].price
        var count = 0

        holder.itemView.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            count += 1
            val amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
            amount.text = "$count"
        }
        holder.itemView.findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
            val amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
            if (count <= 0) {
                amount.text = "0"
            } else {
                count -= 1
                amount.text = "$count"
            }
        }
        holder.itemView.findViewById<ImageView>(R.id.iv_remove).setOnClickListener {
            caps.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return caps.size
    }

    class ViewHolder(
        binding: ListCartBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = binding.ivCap
        val brand: TextView = binding.tvBrand
        val model: TextView = binding.tvModel
        val price: TextView = binding.tvPrice
    }
}