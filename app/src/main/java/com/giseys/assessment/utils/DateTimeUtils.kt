package com.giseys.assessment.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfMonth

class DateTimeUtils {
    companion object{

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(localDateTime: LocalDateTime):String{
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return localDateTime.format(format)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDayOfMonth(): String{
            val now = LocalDateTime.now()
            val firstDay = now.with(firstDayOfMonth())
            return formatDate(firstDay)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getLastDayOfMonth():String{
            val now= LocalDateTime.now()
            val lastDay = now.with(lastDayOfMonth())
            return formatDate(lastDay)

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentMonth(): String{
            val now = LocalDateTime.now()
            return now.month.value.toString()

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentYear(): String{
            val now = LocalDateTime.now()
            return now.year.toString()

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDayOfWeek(): String{
            val now = LocalDateTime.now()
            val firstDay=now.with(previousOrSame(DayOfWeek.MONDAY))
            return formatDate(firstDay)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getLastDateOfWeek():String{
            val now=LocalDateTime.now()
            val lastDay=now.with(nextOrSame(DayOfWeek.SUNDAY))
            return formatDate(lastDay)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDateOfWeekDay(dueDate: String):String{
            val now=LocalDateTime.now()
            val weekDay=now.with(nextOrSame(DayOfWeek.of(dueDate.toInt())))
            return formatDate(weekDay)

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun createDateFromDay(dayOfMonth: String): String{
            val now = LocalDateTime.now()
            val date = now.withDayOfMonth(dayOfMonth.toInt())
            return formatDate(date)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun createDateFromDayAndMonth(day: Int, month: Int): String{
            val now = LocalDateTime.now()
            val date = now.withMonth(month).withDayOfMonth(day)
            return formatDate(date)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDateReadable(date: String): String{
            val originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateTimeFormat = LocalDate.parse(date, originalFormat)
            val readableFormat = DateTimeFormatter.ofPattern("dd MMM, yyyy")
            return readableFormat.format(dateTimeFormat)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun getQuarterStartDate(year: String, quarter: Int): String {
            val yearInt = year.toInt()
            val quarterStartMonth: Int = when (quarter) {
                1 -> 1
                2 -> 4
                3 -> 7
                4 -> 10
                else -> throw IllegalArgumentException("Invalid quarter: $quarter")
            }

            val firstDay = LocalDate.of(yearInt, quarterStartMonth, 1)
            return formatDate(firstDay.atStartOfDay())
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getQuarterEndDate(year: String, quarter: Int): String {
            val yearInt = year.toInt()
            val quarterEndMonth: Int = when (quarter) {
                1 -> 3
                2 -> 6
                3 -> 9
                4 -> 12
                else -> throw IllegalArgumentException("Invalid quarter: $quarter")
            }

            val lastDay = LocalDate.of(yearInt, quarterEndMonth, 1).withDayOfMonth(1).plusMonths(1).minusDays(1)
            return formatDate(lastDay.atStartOfDay())
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun getDateToday():String{
            return formatDate(LocalDateTime.now())
        }








    }
}