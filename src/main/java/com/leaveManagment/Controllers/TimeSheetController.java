package com.leaveManagment.Controllers;

import com.leaveManagment.Entities.TimeSheet;
import com.leaveManagment.Services.TimeSheet.ITimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeSheet")
@RequiredArgsConstructor
public class TimeSheetController {
    private final ITimeSheetService timeSheetService;
    @GetMapping()
    public List<TimeSheet> retrieveAllTimeSheets (){
        return timeSheetService.retrieveAllTimeSheets();
    }

    @GetMapping("/{timeSheet-id}")
    public TimeSheet retrieveTimeSheet(@PathVariable("timeSheet-id") Integer TimeSheetId) {
        return timeSheetService.retrieveTimeSheet(TimeSheetId);
    }

    @PostMapping()
    public void addTimeSheet(@RequestBody TimeSheet timeSheet) {
        timeSheetService.addTimeSheet(timeSheet);
    }

    @PutMapping()
    public TimeSheet updateTimeSheet(@RequestBody TimeSheet timeSheet) {
        return timeSheetService.updateTimeSheet(timeSheet);
    }

    @DeleteMapping("/{timeSheet-id}")
    public void removeTimeSheet(@PathVariable("timeSheet-id") Integer timeSheetId) {
        timeSheetService.deleteTimeSheet(timeSheetId);
    }
    
}
