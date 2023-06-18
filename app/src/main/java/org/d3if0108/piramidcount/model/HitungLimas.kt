package org.d3if0108.piramidcount.model

import org.d3if0108.piramidcount.db.LimasEntity

fun LimasEntity.hitungLimas(): HasilLimas {
    val limas = (editPanjang * editLebar * editTinggi) / 3
    return HasilLimas(limas)
}
