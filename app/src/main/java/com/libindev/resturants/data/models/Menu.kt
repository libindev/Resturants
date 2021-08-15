package com.libindev.resturants.data.models

import com.google.firebase.firestore.DocumentId

data class Menu(@DocumentId val id: String = "", val desc: String = "", val name: String = "")
