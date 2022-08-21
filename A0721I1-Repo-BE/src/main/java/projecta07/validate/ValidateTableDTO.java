package projecta07.validate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import projecta07.dto.TableDTO;

import java.util.regex.Pattern;

@Component
public class ValidateTableDTO implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TableDTO tableDTO = (TableDTO)target;
        if (tableDTO.getCodeTable() == null){
            errors.rejectValue("codeTable", "codeTable.null", "Khong duoc de trong!");
        }
        else if (!Pattern.compile("^TB[0-9]{3}$").matcher(tableDTO.getCodeTable()).find()){
            errors.rejectValue("codeTable", "codeTable.pattern", "Sai dinh dang!");
        }
        if (tableDTO.getStatus() == null){
            errors.rejectValue("status", "status.null", "Khong duoc de trong!");
        }
    }
}
