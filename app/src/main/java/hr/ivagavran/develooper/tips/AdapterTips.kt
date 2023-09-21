package hr.ivagavran.develooper.tips;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import hr.ivagavran.develooper.databinding.RowTipsBinding


class AdapterTips : RecyclerView.Adapter<AdapterTips.HolderTips>, Filterable {
    private val context: Context
    public var tipsArrayList: ArrayList<ModelTips>
    private var filterList: ArrayList<ModelTips>

    private var filter: FilterTips? = null

    private lateinit var binding: RowTipsBinding

    constructor(context: Context, tipsArrayList: ArrayList<ModelTips>) {
        this.context = context
        this.tipsArrayList = tipsArrayList
        this.filterList = tipsArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTips {
        binding = RowTipsBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderTips(binding.root)
    }

    override fun onBindViewHolder(holder: HolderTips, position: Int) {
        val model = tipsArrayList[position]
        val id = model.id
        val tips = model.tips
        val uid = model.uid
        val timestamp = model.timestamp

        holder.tipsTv.text = tips

        holder.deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
                .setMessage("Are sure you want to delete this tips?")
                .setPositiveButton("Confirm"){a, d->
                    Toast.makeText(context, "Deleting", Toast.LENGTH_SHORT).show()
                    deleteTips(model, holder)
                }
                .setNegativeButton("Cancel"){a, d->
                    a.dismiss()
                }
                .show()
        }
    }

    private fun deleteTips(model: ModelTips, holder: HolderTips) {
        val id = model.id
        val ref = FirebaseDatabase.getInstance().getReference("Tips")
        ref.child(id)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e->
                Toast.makeText(context, "Unable to delete due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun getItemCount(): Int {
        return tipsArrayList.size
    }

    inner  class HolderTips(itemView: View): RecyclerView.ViewHolder(itemView){
        //init ui views
        var tipsTv: TextView = binding.tipsTv
        var deleteBtn: ImageButton = binding.deleteBtn
    }

    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterTips(filterList, this)
        }
        return filter as FilterTips
    }
}