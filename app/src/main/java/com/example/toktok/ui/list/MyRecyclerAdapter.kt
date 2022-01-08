import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.R
import com.example.toktok.ui.list.StoreListItem

class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    private var mStoreListItem: ArrayList<StoreListItem>? = null

    interface OnItemClickListener {
        fun onItemClick(v: View, data: StoreListItem, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_store_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mStoreListItem!![position])
    }

    fun setStoreList(list: ArrayList<StoreListItem>?) {
        mStoreListItem = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mStoreListItem!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var profile: ImageView = view.findViewById(R.id.profile) as ImageView
        private var store: TextView = view.findViewById(R.id.store_name_text) as TextView
        private var type: TextView = view.findViewById(R.id.type_name_text) as TextView
        private var location: TextView = view.findViewById(R.id.location_name_text) as TextView
        private var distance: TextView = view.findViewById(R.id.distance_name_text) as TextView
        private var favorite: TextView = view.findViewById(R.id.favorite_name_text) as TextView
        fun onBind(item: StoreListItem) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener { listener?.onItemClick(itemView, item, pos) }
            }

            profile.setImageResource(item.resourceId)
            store.setText(item.store)
            type.setText(item.type)
            location.setText(item.location)
            distance.setText(item.distance)
            favorite.setText(item.favorite)
        }
    }
}