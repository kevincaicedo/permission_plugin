package com.rioapp.permissions_plugin.utils

import android.util.SparseIntArray

abstract class Utils {

    companion object {

        @JvmStatic
        fun asMap(sparseArray: SparseIntArray): Map<Int, Int>? {

            val map = HashMap<Int, Int>(sparseArray.size())
            for (i in 0 until sparseArray.size())
                map[sparseArray.keyAt(i)] = sparseArray.valueAt(i)
            return map
        }
    }

}