package  com.xidu.framework.validation.validator.impl;

import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.AbstractTextValidator;

/**
 * mobile phone validator
 */
public class MobilePhoneTextValidator extends AbstractTextValidator {

    /**
     * 默认手机号码错误提示信息
     */
    private static final String DEFAULT_MESSAGE_KEY = "excel.validation.mobilePhone";
    /**
     * 手机号码正则
     */
    private static final Pattern pattern = Pattern.compile("^1\\d{10}$");

    public MobilePhoneTextValidator() {
    }

    public MobilePhoneTextValidator(String msgKey) {
        this.msgKey = msgKey;
    }

    @Override
    public String validate(String text, Locale locale) {
        if (StringUtils.isNotBlank(text) && !pattern.matcher(text).matches()) {
            if (StringUtils.isBlank(msgKey)) {
                msgKey = DEFAULT_MESSAGE_KEY;
            }
            return getMessageSource().getMessage(msgKey, new Object[]{text}, locale);
        }
        return "";
    }
}
