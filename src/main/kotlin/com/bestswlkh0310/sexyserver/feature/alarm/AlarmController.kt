package com.bestswlkh0310.sexyserver.feature.alarm

import com.bestswlkh0310.sexyserver.dto.AlarmDto
import com.bestswlkh0310.sexyserver.dto.RoomDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/alarm")
class AlarmController {

    var map: ArrayList<RoomDto> = arrayListOf()

    private val prefix = "<?> "
    private val prefix2 = "매일매일"

    @PostMapping("/", "")
    fun Alarm(
        @RequestBody alarmRequest: AlarmDto
    ): ResponseEntity<String> {
        if (alarmRequest.packageName != "com.kakao.talk" || !(alarmRequest.roomName.startsWith(prefix) || alarmRequest.roomName.startsWith(prefix2))) {
            return ResponseEntity.status(400).body("is not open chat msg")
        }
        println(alarmRequest)
        val roomName = alarmRequest.roomName.replace(prefix, "")
        val newAlarmDto = alarmRequest.copy(roomName = roomName)
        var isExist = false
        map.forEach {
            if (it.roomName == roomName) {
                it.alarmList.add(newAlarmDto)
                isExist = true
            }
        }
        if (!isExist) {
            map.add(
                RoomDto(
                    roomName = roomName,
                    alarmList = arrayListOf(newAlarmDto)
                )
            )
        }

        return ResponseEntity.status(201).body("success")
    }

    @GetMapping("/", "")
    fun AllAlarm(): ResponseEntity<ArrayList<RoomDto>> {
        return ResponseEntity.ok(map)
    }
}