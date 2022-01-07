package com.example.toktok.ui.list

class StoreListItem() {

    var store: String? = null
    var type: String? = null
    var location: String? = null
    var distance: String? = null
    var favorite: String? = null
    var resourceId = 0

    constructor(
        resourceId: Int,
        store: String?,
        type: String?,
        location: String?,
        distance: String?,
        favorite: String?
    ) : this() {
        this.resourceId = resourceId
        this.store = store
        this.type = type
        this.location = location
        this.distance = distance
        this.favorite = favorite
    }
}