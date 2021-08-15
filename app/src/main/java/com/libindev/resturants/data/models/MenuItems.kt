package com.libindev.resturants.data.models

import com.google.firebase.firestore.DocumentId

data class MenuItems(
    @DocumentId val id: String = "",
    val desc: String = "",
    val name: String = "",
    val url: String = ""
)
