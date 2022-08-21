package projecta07.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projecta07.service.IIncomeService;
import java.util.Optional;

@RestController
@RequestMapping("/manager/api/income")
@CrossOrigin("*")
public class IncomeController {
    @Autowired
    private IIncomeService iIncomeService;

    // Author: ToanPT

    // THEO NGÀY
    @GetMapping("/day")
    public ResponseEntity incomeToDay(){
        double totalDay = Double.parseDouble(iIncomeService.sumTotalOderDay());
        return new ResponseEntity(totalDay, HttpStatus.OK);
    }

    // THEO TUẦN
    @GetMapping("/week")
    public ResponseEntity incomeToWeek() {
        double totalWeek = Double.parseDouble(iIncomeService.sumTotalOrderWeek());
        return new ResponseEntity(totalWeek, HttpStatus.OK);
    }

    // THEO THÁNG
    @GetMapping("/month")
    public ResponseEntity incomeToMonth() {
        double totalMonth = Double.parseDouble(iIncomeService.sumTotalOrderMonth());
        return new ResponseEntity(totalMonth, HttpStatus.OK);
    }

    // THEO NĂM
    @GetMapping("/year")
    public ResponseEntity incomeToYear() {
        double totalYear = Double.parseDouble(iIncomeService.sumTontalOrderYear());
        return new ResponseEntity(totalYear, HttpStatus.OK);
    }

    // TỪ NGÀY NÀY ĐẾN NGÀY KHÁC C1
//    @GetMapping("/daytoday/{startDay}/{endDay}")
//    public ResponseEntity incomeDayToDay(@PathVariable String startDay, @PathVariable String endDay) {
//        double totalDayToDay = Double.parseDouble(iIncomeService.sumTotalStartDayToEndDay(startDay, endDay));
//        return new ResponseEntity(totalDayToDay, HttpStatus.OK);
//    }

    // TỪ NGÀY NÀY ĐẾN NGÀY KHÁC C2
    @GetMapping("/daytoday")
    public ResponseEntity incomeDayToDay(@RequestParam(value = "startDay", required = false) Optional<String> startDay,
                                         @RequestParam(value = "endDay", required = false) Optional<String> endDay) {
        System.out.println(startDay.get() + " - " + endDay.get());
        double totalDayToDay = 0;
        if (startDay.isPresent() && endDay.isPresent()) {
            totalDayToDay = Double.parseDouble(iIncomeService.sumTotalStartDayToEndDay(startDay.get(), endDay.get()));
        }
        return new ResponseEntity(totalDayToDay, HttpStatus.OK);
    }

    // tổng số sản phẩm cafe
    @GetMapping("countCafe")
        public ResponseEntity countProductCafe() {
        double countCafe = Double.parseDouble(iIncomeService.countProductCafe());
        return new ResponseEntity(countCafe, HttpStatus.OK);
    }

    // tổng số sản phẩm trà
    @GetMapping("countTea")
    public ResponseEntity countProductTea() {
        double countTea = Double.parseDouble(iIncomeService.countProductTea());
        return new ResponseEntity(countTea, HttpStatus.OK);
    }

    // tổng số sản phẩm bánh
    @GetMapping("countCake")
    public ResponseEntity countProductCake() {
        double countCake = Double.parseDouble(iIncomeService.countProductCafe());
        return new ResponseEntity(countCake, HttpStatus.OK);
    }

    // tổng số sản phẩm khác
    @GetMapping("countOther")
    public ResponseEntity countProductOther() {
        double countOther = Double.parseDouble(iIncomeService.countProductOther());
        return new ResponseEntity(countOther, HttpStatus.OK);
    }
}
