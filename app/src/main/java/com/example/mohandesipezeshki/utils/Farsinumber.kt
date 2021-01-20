package com.example.mohandesipezeshki.utils

 object  Farsinumber {


    var farsinumber =
        arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")

    fun farsinumber(text: String): String? {
        if (text.isEmpty()) {
            return ""
        }
        var out = ""
        val length = text.length
        for (i in 0 until length) {
            val c = text[i]
            out = if (c in '0'..'9') {
                val number = c.toString().toInt()
                out + farsinumber[number]
            } else if (c == ',' || c == ',') {
                "$out,"
            } else {
                out + c
            }
        }
        return out
    }

}