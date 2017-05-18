package com.tantusdata.training.second

case class Event(eventTime: String,
                 country:String,
                 userID: String,
                 eventType:String,
                 operatingSystem: String,
                 operatingSystemVersion: String,
                 appVersion:String,
                 registrationMonth: String,
                 xCoord:Double,
                 yCoord: Double
                 )
