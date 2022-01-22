package kg.geektech.customcaps.ui.fragments.mainPage

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.databinding.ListBestsellersBinding
import kg.geektech.customcaps.models.ModelCap

class MainAdapter(private val caps: List<ModelCap>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListBestsellersBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        caps[position].img?.let { holder.img.setImageResource(it) }
        holder.brand.text = caps[position].brand
        holder.model.text = caps[position].model
        holder.price.text = caps[position].price
        if (caps[position].oldPrice != null) {
            holder.priceOld.text = caps[position].oldPrice
            holder.priceOld.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) {

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener

    }

    override fun getItemCount(): Int {
        return caps.size
    }

    class ViewHolder(binding: ListBestsellersBinding, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = binding.ivBestseller
        val brand: TextView = binding.tvBrand
        val model: TextView = binding.tvModel
        val price: TextView = binding.tvPrice
        val priceOld: TextView = binding.tvPriceOld

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }
}