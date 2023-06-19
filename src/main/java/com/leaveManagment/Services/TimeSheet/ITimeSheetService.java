package com.leaveManagment.services.TimeSheet;

import com.leaveManagment.entities.TimeSheet;

import java.util.List;

public interface ITimeSheetService {
    List<TimeSheet> retrieveAllTimeSheets();

    TimeSheet addTimeSheet(TimeSheet TimeSheet);

    TimeSheet updateTimeSheet (TimeSheet TimeSheet);

    TimeSheet retrieveTimeSheet (Integer idTimeSheet);

    void deleteTimeSheet(Integer idTimeSheet);
}