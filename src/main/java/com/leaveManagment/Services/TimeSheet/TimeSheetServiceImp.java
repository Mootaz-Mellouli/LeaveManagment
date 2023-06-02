package com.leaveManagment.Services.TimeSheet;


import com.leaveManagment.Entities.TimeSheet;
import com.leaveManagment.Repositories.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImp implements ITimeSheetService {

    private final TimeSheetRepository TimeSheetRepository;

    @Override
    public List<TimeSheet> retrieveAllTimeSheets() {
        return TimeSheetRepository.findAll();
    }

    @Override
    public TimeSheet addTimeSheet(TimeSheet TimeSheet) {
        return TimeSheetRepository.save(TimeSheet);
    }

    @Override
    public TimeSheet updateTimeSheet(TimeSheet TimeSheet) {
        return TimeSheetRepository.save(TimeSheet);
    }

    @Override
    public TimeSheet retrieveTimeSheet(Integer idTimeSheet) {
        return TimeSheetRepository.findById(idTimeSheet).orElse(null);
    }

    @Override
    public void deleteTimeSheet(Integer idTimeSheet) {
        TimeSheetRepository.deleteById(idTimeSheet);
    }
}
