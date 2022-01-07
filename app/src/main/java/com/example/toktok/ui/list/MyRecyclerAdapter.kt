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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_store_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mStoreListItem!![position])
    }

    fun setFriendList(list: ArrayList<StoreListItem>?) {
        mStoreListItem = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mStoreListItem!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profile: ImageView
        var store: TextView
        var type: TextView
        var location: TextView
        var distance: TextView
        var favorite: TextView
        fun onBind(item: StoreListItem) {
            profile.setImageResource(item.resourceId)
            store.setText(item.store)
            type.setText(item.type)
            location.setText(item.location)
            distance.setText(item.distance)
            favorite.setText(item.favorite)
        }

        init {
            profile = itemView.findViewById(R.id.profile) as ImageView
            store = itemView.findViewById(R.id.store_name_text)
            type = itemView.findViewById(R.id.type_name_text)
            location = itemView.findViewById(R.id.location_name_text)
            distance = itemView.findViewById(R.id.distance_name_text)
            favorite = itemView.findViewById(R.id.favorite_name_text)
        }
    }
}