package projecta07.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import projecta07.config.MailConfig;
import projecta07.config.MyConstants;
import projecta07.exception.EmployeeNotFoundException;
import projecta07.model.Employee;
import projecta07.service.IEmployeeService;


import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/account")
public class ForgotPasswordController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/forgot_password")
    public ResponseEntity<String> processForgotPasswordForm(@RequestBody String comingEmail) {
        String message = "";
        String email = comingEmail;
        String token = RandomString.make(45);

        try {
            // Hàm kiêm tra email này của người dùng nào, nếu không có trả về exception, có sẽ thực hiện set token.
            employeeService.updateResetPasswordToken(token, email);
            // Tạo đường dẫn đến form đổi mật khẩu
             String resetPasswordLink = "http://localhost:4200/forgot-password/reset-password/"+token;//moi
            // Hàm để gửi email
            sendEmail(email, resetPasswordLink);
            message = "Chúng tôi đa gửi link thay đổi mật khẩu. Vui lòng mở email để kiểm tra";

        } catch (EmployeeNotFoundException exception) {
            message = exception.getMessage();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedEncodingException | MessagingException exception) {
            message = "Lỗi khi gửi email";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    // Hàm được định nghĩa.
    private ResponseEntity<Void> sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");

        //...
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
        helper.setFrom("contact@a0721i1.com", "A0721i1 group- Coffee shop");
        helper.setTo(email);
        String subject = "Đây là link thay đổi mật khẩu cho bạn:";
        String content = "<p>Xin chào,</p>"
                + "<p>Tôi biết bạn đang quên mật khẩu ở trang web chúng tôi, tôi rất lấy làm tiếc,</p>"
                + "<p>Đừng lo lắng,bạn có thể nhấn vào nút ở bên dưới để tạo lại mật khẩu cho bạn: </p>"
                + "<p><b> <a href=\""+ resetPasswordLink +"\">Lấy lại mật khẩu</a><b></p>"
                + "<p>Bỏ qua email này nếu bạn đã nhớ mật khẩu</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        // Đoạn nay de gui email/ không có sẽ rơi exception
        mailSender.send(message);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<String> processResetPassword(@RequestParam(value="token", required = false) String comingToken,
                                                       @RequestParam(value="password", required = false) String newPassword) {
        String message = "";      // Tin nhắn gửi lên giao diện
        String token = comingToken;
        String password=newPassword;
        Employee employee = employeeService.getByResetPasswordToken(token);
        if (employee == null) {
            message = "token không hợp lệ";
        } else {
            employeeService.updatePassword(employee, password);
            message = "Đã thay đổi password thành công";
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
