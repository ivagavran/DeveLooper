package hr.ivagavran.develooper.quiz;

import hr.ivagavran.develooper.quiz.Question

data class Quiz (
    var id : String = "",
    var title: String = "",
    var questions: MutableMap<String, Question> = mutableMapOf()
)