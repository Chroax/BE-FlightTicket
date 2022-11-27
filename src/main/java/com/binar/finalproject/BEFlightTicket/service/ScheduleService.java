package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest, UUID scheduleId);
    List<ScheduleResponse> searchAirplaneSchedule(String airplaneName);
    List<ScheduleResponse> searchRouteSchedule(UUID routeId);
    List<ScheduleResponse> getAllSchedule();
}
