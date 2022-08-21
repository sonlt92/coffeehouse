package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.FeedbackDTO;
import projecta07.model.Feedback;
import projecta07.service.IFeedbackService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("manager/api/feedback")
    public ResponseEntity<Iterable<Feedback>> findAllFeedback(@RequestParam int index) {
        Pageable pageable = PageRequest.of(index , 10);
        Page<Feedback> feedbackList = feedbackService.findAll(pageable);
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback-not-pagination/")
    public ResponseEntity<List<Feedback>> findAllFeedbackNotPagination() {
        List<Feedback> feedbackList = feedbackService.findAll();
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback/search")
    public ResponseEntity<Iterable<Feedback>> findAllFeedbackByDateFeedback(@RequestParam(defaultValue = "") String date,
                                                                            @RequestParam int index)  {
        String dateSearch = date.replace("/","-");
        Pageable pageable = PageRequest.of(index , 10);
        Page<Feedback> feedbackListByDate = feedbackService.findAllFeedbackByDateFeedback(dateSearch, pageable);
        if (feedbackListByDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackListByDate,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback/search-not-pagination")
    public ResponseEntity<List<Feedback>> findAllFeedbackByDateFeedbackNotPagination(@RequestParam(defaultValue = "") String date) {
        List<Feedback> feedbackList = feedbackService.findAllFeedbackByDateFeedbackNotPagination(date);
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback/view")
    public ResponseEntity<Feedback> findFeedbackById(@RequestParam Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findFeedbackById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackOptional.get(), HttpStatus.OK);
    }

    @PostMapping("api/feedback/createFeedback")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback, BindingResult bindingResult) {
        Feedback feedbackSaved = new Feedback();
        String codeFeedback = "FB-" + Math.floor(Math.random() * 999);
        feedbackSaved.setCodeFeedback(codeFeedback);
        feedbackSaved.setDateFeedback(LocalDate.now());
        feedbackSaved.setNamePeopleFeedback(feedback.getNamePeopleFeedback());
        feedbackSaved.setImageFeedback(feedback.getImageFeedback());
        feedbackSaved.setEmailPeopleFeedback(feedback.getEmailPeopleFeedback());

        /* Danh sách từ loại bỏ */
        List<String> matches = Arrays.asList("đm", "vãi", "ngu" , "gớm", "xấu");
        String feedbackEdit = feedback.getContentFeedback().toLowerCase();

        String star = "**";

        char contentFeedbackAr[] = feedbackEdit.toCharArray();
        int lenContentFeedback = contentFeedbackAr.length;
        int index = 0;
        for (int i = 0; i < lenContentFeedback; i++)
        {
            int j;
            for (j = 0; j < i; j++)
            {
                /* Nếu giống nhau thì bỏ qua */
                if (contentFeedbackAr[i] == contentFeedbackAr[j] && contentFeedbackAr[i] == contentFeedbackAr[j+2])
                {
                    break;
                }
            }
            if (j == i)
            {
                contentFeedbackAr[index++] = contentFeedbackAr[i];
            }
        }

        /* Lấy content sau khi cắt */
        String contentAfterCut = String.valueOf(Arrays.copyOf(contentFeedbackAr, index));

        /* Cắt dấu cách */
        String[] arStr = contentAfterCut.split(",|-|\\.| ");

        String result = "";

        /* Lấy độ dài lớn nhất */
        int maxLength = Math.max(arStr.length, matches.size());

        /* Xóa từ */
        for (int i = 0; i < maxLength-1; i++) {
            if (arStr.length == i) {
                break;
            } else {
                if(matches.contains(arStr[i])) {
                    arStr[i] = star;
                    result += arStr[i] + " ";
                }
                result += arStr[i] + " ";
            }
        }

        feedbackSaved.setContentFeedback(result);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        feedbackService.saveFeedback(feedbackSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}