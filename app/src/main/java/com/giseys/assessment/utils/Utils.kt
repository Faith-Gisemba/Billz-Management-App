package com.giseys.assessment.utils


import java.text.DecimalFormat
class Utils {
    companion object{
        fun formatCurrency(amount:Double):String{
            val fmt = DecimalFormat("KES #,###")
            return fmt.format(amount)
        }
    }
}




