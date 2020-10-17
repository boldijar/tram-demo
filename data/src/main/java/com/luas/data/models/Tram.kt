package com.luas.data.models

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Tram(
    @Attribute(name = "destination")
    val destination: String,
    @Attribute(name = "dueMins")
    val dueMins: String
)