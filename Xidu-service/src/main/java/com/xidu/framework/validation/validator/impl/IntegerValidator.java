package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 * Integer Validator
 */
public class IntegerValidator extends AbstractTextValidator {

    /**
     * 默认整型错误提示信息
     */
    private static final String DEFAULT_MESSAGE_KEY = "excel.validation.integer";
    
    /**
     * 整型正则
     */
    private static final Pattern pattern = Pattern.compile("\\d+");
    
    private int maxLength;
    
    public IntegerValidator(int maxLength){
        this.maxLength = maxLength;
    }
    
    public IntegerValidator(int maxLength, String msgKey){
        this.maxLength = maxLength;
        this.msgKey = msgKey;
    }
    
    @Override
    public String validate(String text, Locale locale) {
    	try{
    		Integer.parseInt(text);
    	}catch(Exception e){
    		if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, new Object[]{maxLength}, locale);
    	}
    	//|| !pattern.matcher(text).matches()
        if (StringUtils.isBlank(text)||(StringUtils.isNotBlank(text) && text.length() > maxLength)) {
            if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, new Object[]{maxLength}, locale);
        }
        return "";
    }
    
}
