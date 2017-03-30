package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 *
 */
public class LengthTextValidator extends AbstractTextValidator {

    private int minLength;
    private int maxLength;
    
    public LengthTextValidator(String msgKey, int minLength, int maxLength){
        this.msgKey = msgKey;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    @Override
    public String validate(String text, Locale locale) {
        
        if(null != text && ( text.getBytes().length < minLength || text.getBytes().length > maxLength)){
            return getMessageSource().getMessage(msgKey, new Object[]{minLength,maxLength}, locale);
        }
        return "";
    }

}
