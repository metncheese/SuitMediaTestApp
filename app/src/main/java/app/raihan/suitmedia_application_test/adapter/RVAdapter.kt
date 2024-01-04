package app.raihan.suitmedia_application_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.raihan.suitmedia_application_test.data.response.DataItem
import app.raihan.suitmedia_application_test.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class RVAdapter: RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    private val userList = ArrayList<DataItem>()
    private var onClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ItemViewHolder((view))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    inner class ItemViewHolder(private val itemBinding: ItemRowUserBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: DataItem) {
            itemBinding.root.setOnClickListener{
                onClickCallback?.onItemClicked(data)
            }
            itemBinding.apply {
                tvFirstname.text = data.firstName
                tvLastname.text = data.lastName
                tvEmail.text = data.email
                Glide.with(itemView)
                    .load(data.avatar)
                    .circleCrop()
                    .into(photoUser)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataItem)
    }

    fun setClickCallback(ItemClickCallback: OnItemClickCallback){
        this.onClickCallback = ItemClickCallback
    }
    fun clearUsers() {
        this.userList.clear()
        notifyDataSetChanged()
    }

    fun setList(users:ArrayList<DataItem>){
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
}