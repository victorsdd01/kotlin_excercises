package com.victorsdd.androidmaster.my_app.superHero.models
import com.google.gson.annotations.SerializedName

data class SuperHeroResponse (
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHero>
)

data class SuperHeroDetailResponse (
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val image: Image,
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence : String,
    @SerializedName("strength") val strength : String,
    @SerializedName("speed") val speed : String,
    @SerializedName("durability") val durability : String,
    @SerializedName("power") val power : String,
    @SerializedName("combat") val combat : String,
)
data class Appearance (
    @SerializedName("gender")  val gender: String,
    @SerializedName("race") val race: String,
    @SerializedName("height") val height: List<String>,
    @SerializedName("weight") val weight: List<String>,
    @SerializedName("eyeColor") val eyeColor: String,
    @SerializedName("hairColor")  val hairColor: String
)

data class Biography (
    @SerializedName("fullName") val fullName: String,
    @SerializedName("alterEgos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("placeOfBirth") val placeOfBirth: String,
    @SerializedName("firstAppearance") val firstAppearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
)

data class Connections (
    @SerializedName("groupAffiliation") val groupAffiliation: String,
    @SerializedName("relatives") val relatives: String
)

data class Work (
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String
)
data class SuperHero(
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("image") val image : Image,
)
data class Image (
    @SerializedName("url") val url : String,
)
