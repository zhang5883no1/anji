package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 * required validator
 */
public class RequiredTextValidator extends AbstractTextValidator {
    
    /**
     * 默认非空错误提示信息
     */
    private static final String DEFAULT_MESSAGE_KEY = "excel.validation.notEmpty";
    
    public RequiredTextValidator(){
    }
    
    public RequiredTextValidator(String msgKey){
        this.msgKey = msgKey;
    }
    @Override
    public String validate(String text, Locale locale) {
        if(StringUtils.isBlank(text)){
            if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, null, locale);
        }
        return "";
    }

}
