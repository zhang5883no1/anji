package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 * max length validator
 */
public class MaxCharLengthTextValidator extends AbstractTextValidator {
    
    /**
     * 默认字符超过长度的错误提示信息
     */
    private static final String DEFAULT_MESSAGE_KEY = "excel.validation.maxLength";
    
    private int maxLength;
    
    public MaxCharLengthTextValidator(int maxLength){
        this.maxLength = maxLength;
    }
    
    public MaxCharLengthTextValidator(String msgKey, int maxLength){
        this.msgKey = msgKey;
        this.maxLength = maxLength;
    }
    @Override
    public String validate(String text, Locale locale) {
        if (StringUtils.isNotBlank(text) && text.length()> maxLength) {
            if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, new Object[]{maxLength}, locale);
        }
        return "";
    }
}
