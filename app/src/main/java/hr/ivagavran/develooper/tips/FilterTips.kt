package hr.ivagavran.develooper.tips;

import android.widget.Filter

class FilterTips: Filter {
    private var filterList: ArrayList<ModelTips>
    private var adapterTips: AdapterTips

    constructor(filterList: ArrayList<ModelTips>, adapterTips: AdapterTips) : super() {
        this.filterList = filterList
        this.adapterTips = adapterTips
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val results = FilterResults()

        if (constraint != null && constraint.isNotEmpty()){
            constraint = constraint.toString().uppercase()
            val filteredModels:ArrayList<ModelTips> = ArrayList()
            for (i in 0 until  filterList.size){
                if (filterList[i].tips.uppercase().contains(constraint)){
                    filteredModels.add(filterList[i])
                }
            }
            results.count = filteredModels.size
            results.values = filteredModels

        }
        else{
            results.count = filterList.size
            results.values = filterList
        }

        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        adapterTips.tipsArrayList = results.values as ArrayList<ModelTips>
        adapterTips.notifyDataSetChanged()
    }
}