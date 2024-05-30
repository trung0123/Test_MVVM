package jilnesta.com.testmvvm.presentation.component.home.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataHome(
    @SerializedName("next_lesson")
    var nextLesson: Lesson,
    @SerializedName("overall_rank_lesson")
    var overallRankLessons: List<Lesson>,
    @SerializedName("weekly_ranking_lesson")
    var weeklyRankLessons: List<Lesson>,
    @SerializedName("recommend_lesson")
    var recommendLessons: List<Lesson>,
    @SerializedName("newest_lesson")
    var newestLessons: List<Lesson>
) : Serializable
