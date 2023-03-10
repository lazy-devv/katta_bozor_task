package uz.lazydevv.kattabozortask.presenter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.lazydevv.kattabozortask.data.models.ProductM
import uz.lazydevv.kattabozortask.databinding.ItemRvProductBinding

class RvProductsAdapter : RecyclerView.Adapter<RvProductsAdapter.ProductVh>() {

    private val diffUtilCallback = object : DiffUtil.ItemCallback<ProductM>() {
        override fun areItemsTheSame(oldItem: ProductM, newItem: ProductM): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductM, newItem: ProductM): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val diffUtil = AsyncListDiffer(this, diffUtilCallback)

    inner class ProductVh(private val itemBinding: ItemRvProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(product: ProductM) {
            with(itemBinding) {
                tvName.text = product.name
                tvBrand.text = product.brand
                tvMerchant.text = product.merchant
                ivImg.load(product.imgUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVh {
        return ProductVh(ItemRvProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = diffUtil.currentList.size

    override fun onBindViewHolder(holder: ProductVh, position: Int) {
        holder.onBind(diffUtil.currentList[position])
    }
}