package projecta07.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import projecta07.service.IFeedbackService;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FeedbackDTO  implements  Validator{
    private Long idFeedback;
    private String codeFeedback;
    private LocalDate dateFeedback;

    @NotEmpty(message = "cannot be empty")
    @Length(min = 3 , max = 50 , message = "must be between 3 and 50")
    @Column(name = "content_feedback")
    private String contentFeedback;

    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+", message = "Định dạng tên không đúng")
    @Length(min = 3 , max = 50 , message = "must be between 3 and 50")
    @Column(name = "name_people_feedback")
    private String namePeopleFeedback;

    @NotEmpty
    @Email
    @Column(name = "email_people_feedback")
    private String emailPeopleFeedback;

    private String imageFeedback;

    @Valid
    private IFeedbackService feedbackService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public Long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getCodeFeedback() {
        return codeFeedback;
    }

    public void setCodeFeedback(String codeFeedback) {
        this.codeFeedback = codeFeedback;
    }

    public LocalDate getDateFeedback() {
        return dateFeedback;
    }

    public void setDateFeedback(LocalDate dateFeedback) {
        this.dateFeedback = dateFeedback;
    }

    public String getContentFeedback() {
        return contentFeedback;
    }

    public void setContentFeedback(String contentFeedback) {
        this.contentFeedback = contentFeedback;
    }

    public String getNamePeopleFeedback() {
        return namePeopleFeedback;
    }

    public void setNamePeopleFeedback(String namePeopleFeedback) {
        this.namePeopleFeedback = namePeopleFeedback;
    }

    public String getEmailPeopleFeedback() {
        return emailPeopleFeedback;
    }

    public void setEmailPeopleFeedback(String emailPeopleFeedback) {
        this.emailPeopleFeedback = emailPeopleFeedback;
    }

    public String getImageFeedback() {
        return imageFeedback;
    }

    public void setImageFeedback(String imageFeedback) {
        this.imageFeedback = imageFeedback;
    }

    public IFeedbackService getFeedbackService() {
        return feedbackService;
    }

    public void setFeedbackService(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
