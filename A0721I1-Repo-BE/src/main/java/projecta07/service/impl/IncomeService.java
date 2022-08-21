package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
import projecta07.repository.IIncomeRepository;
import projecta07.service.IIncomeService;

import java.util.List;

@Service
public class IncomeService implements IIncomeService {
    @Autowired
    private IIncomeRepository iIncomeRepository;


    @Override
    public String sumTotalStartDayToEndDay(String startDay, String endDay) {
        return iIncomeRepository.sumTotalStartDayToEndDay(startDay, endDay);
    }

    @Override
    public String sumTotalOderDay() {
        return iIncomeRepository.sumTotalOrderDay();
    }

    @Override
    public String sumTotalOrderWeek() {
        return iIncomeRepository.sumTotalOrderWeek();
    }

    @Override
    public String sumTotalOrderMonth() {
        return iIncomeRepository.sumTotalOrderMonth();
    }

    @Override
    public String sumTontalOrderYear() {
        return iIncomeRepository.sumTontalOrderYear();
    }

    @Override
    public String countProductCafe() {
        return iIncomeRepository.countProductCafe();
    }

    @Override
    public String countProductTea() {
        return iIncomeRepository.countProductTea();
    }

    @Override
    public String countProductCake() {
        return iIncomeRepository.countProductCake();
    }

    @Override
    public String countProductOther() {
        return iIncomeRepository.countProductOther();
    }
}
