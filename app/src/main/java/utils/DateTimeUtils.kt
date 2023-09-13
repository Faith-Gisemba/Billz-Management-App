package utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster.*
import java.time.DayOfWeek
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfMonth

class DateTimeUtils {
    companion object{
        fun formatDateddMMyyyy(localDateTime: LocalDateTime):String{
            val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return localDateTime.format(format)
        }

        fun getFirstDayOfMonth(): String{
            val now = LocalDateTime.now()
            val firstDay = now.with(firstDayOfMonth())
            return formatDateddMMyyyy(firstDay)
        }
        fun getLastDayOfMonth():String{
            val now= LocalDateTime.now()
            val lastDay = now.with(lastDayOfMonth())
            return formatDateddMMyyyy(lastDay)

        }
        fun getCurrentMonth(): String{
            val now = LocalDateTime.now()
            return now.month.value.toString()

        }
        fun getCurrentYear(): String{
            val now = LocalDateTime.now()
            return now.year.toString()

        }

        fun getFirstDayOfWeek(): String{
            val now = LocalDateTime.now()
            val firstDay=now.with(previousOrSame(DayOfWeek.MONDAY))
            return formatDateddMMyyyy(firstDay)
        }
        fun getLastDateOfWeek():String{
            val now=LocalDateTime.now()
            val lastDay=now.with(nextOrSame(DayOfWeek.SUNDAY))
            return formatDateddMMyyyy(lastDay)
        }
        fun getDateOfWeekDay(dueDate: String):String{
            val now=LocalDateTime.now()
            val weekDay=now.with(nextOrSame(DayOfWeek.of(dueDate.toInt())))
            return formatDateddMMyyyy(weekDay)

        }





    }
}