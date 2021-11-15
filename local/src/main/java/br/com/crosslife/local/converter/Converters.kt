package br.com.crosslife.local.converter

import androidx.room.TypeConverter
import br.com.crosslife.local.entities.Notice
import br.com.crosslife.local.entities.Train
import com.squareup.moshi.Moshi


class Converters {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromNoticeToJson(notice: Notice) = notice.toJson()

    @TypeConverter
    fun fromJsonToNotice(json: String) = json.to<Notice>()

    @TypeConverter
    fun fromTrainToJson(train: Train) = train.toJson()

    @TypeConverter
    fun fromJsonToTrain(json: String) = json.to<Train>()

    private inline fun <reified T> T.toJson(): String = moshi.adapter(T::class.java).toJson(this)

    private inline fun <reified T> String.to(): T? = moshi.adapter(T::class.java).fromJson(this)
}
