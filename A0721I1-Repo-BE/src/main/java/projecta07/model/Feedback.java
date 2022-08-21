package projecta07.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Long idFeedback;

    @Column(name = "code_feedback")
    private String codeFeedback;

    @Column(name = "date_feedback")
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

    @Column(name = "image_feedback")
    private String imageFeedback;

    public Feedback() {
    }

    public Feedback(String contentFeedback, String namePeopleFeedback, String emailPeopleFeedback, String imageFeedback) {
        this.contentFeedback = contentFeedback;
        this.namePeopleFeedback = namePeopleFeedback;
        this.emailPeopleFeedback = emailPeopleFeedback;
        this.imageFeedback = imageFeedback;
    }

    public Feedback(Long idFeedback, String codeFeedback, String dateFeedback, String contentFeedback, String namePeopleFeedback, String emailPeopleFeedback, String imageFeedback) {
        this.idFeedback = idFeedback;
        this.codeFeedback = codeFeedback;
        this.contentFeedback = contentFeedback;
        this.namePeopleFeedback = namePeopleFeedback;
        this.emailPeopleFeedback = emailPeopleFeedback;
        this.imageFeedback = imageFeedback;
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
}
