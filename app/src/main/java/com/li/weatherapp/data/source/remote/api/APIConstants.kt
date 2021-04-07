package com.li.weatherapp.data.source.remote.api

object APIConstants {
    const val SCHEME_HTTPS = "https"
    const val SCHEME_HTTP = "http"
    const val OPENWEATHER_AUTHORITY_API = "api.openweathermap.org"
    const val GEODB_AUTHORITY_API = "geodb-free-service.wirefreethought.com"
    const val OPENWEATHER_CONTENT_ONE_CALL = "/data/2.5/onecall"
    const val OPENWEATHER_CONTENT_WEATHER = "/data/2.5/weather"
    const val OPENWEATHER_CONTENT_AIR_POLLUTION = "/data/2.5/air_pollution"

    const val OPENWEATHER_API_KEY = "appid"
    const val OPENWEATHER_LAT = "lat"
    const val OPENWEATHER_LON = "lon"
    const val OPENWEATHER_EXCLUDE = "exclude"
    const val OPENWEATHER_EXCLUDE_CURRENT_HOURLY_MINUTELY = "current,hourly,minutely"
    const val OPENWEATHER_EXCLUDE_CURRENT_MINUTELY_DAILY = "current,daily,minutely"
    const val OPENWEATHER_UNITS = "units"
    const val OPENWEATHER_METRIC = "metric"
    const val OPENWEATHER_HISTORY = "timemachine"
    const val OPENWEATHER_TIME = "dt"

    const val GEODB_LIMIT = "limit"
    const val GEODB_LIMIT_VALUE = "8"
    const val GEODB_CONTENT_V1 = "/v1/geo/cities"
    const val GEODB_OFFSET = "offset"
    const val GEODB_OFFSET_VALUE = "0"
    const val GEODB_NAME_PREFIX = "namePrefix"
}
