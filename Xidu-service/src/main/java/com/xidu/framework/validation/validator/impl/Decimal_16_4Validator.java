package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 * Decimal_16_4 Validator
 */
public class Decimal_16_4Validator extends AbstractTextValidator {

    /**
     * 默认金额错误提示信息
     */
    private static final String DEFAULT_MESSAGE_KEY = "excel.validation.decimal_16_4";
    /**
     * 正则
     */
    private static final Pattern pattern = Pattern.compile("^\\d{0,12}(\\.\\d{1,4})?$");
    
    public Decimal_16_4Validator(){}
    
    public Decimal_16_4Validator(String msgKey){
        this.msgKey = msgKey;
    }
    @Override
    public String validate(String text, Locale locale) {
        if (StringUtils.isNotBlank(text) && !pattern.matcher(text).matches()) {
            if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, new Object[]{null}, locale);
        }
        return "";
    }
}
