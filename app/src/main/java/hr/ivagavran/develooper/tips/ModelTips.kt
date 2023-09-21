package hr.ivagavran.develooper.tips;

class ModelTips {
    var id:String = ""
    var tips:String = ""
    var timestamp:Long = 0
    var uid:String = ""

    constructor()

    constructor(id: String, tips: String, timestamp: Long, uid: String) {
        this.id = id
        this.tips = tips
        this.timestamp = timestamp
        this.uid = uid
    }
}