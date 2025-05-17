package com.it.shka.searchjobapp

enum class IconId(val iconId:String) {
    NEAR_VACANCIES("near_vacancies"),
   LEVEL_UP_RESURS("level_up_resume"),
    TEMPORARY_JOB("temporary_job");

    companion object{
        fun fromIconIdStrinf(text: String): IconId? {
            return values().find { it.iconId == text }
        }
    }

}