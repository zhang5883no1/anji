package  com.xidu.framework.validation.validator;


import org.springframework.context.MessageSource;

import com.xidu.framework.common.util.ApplicationContextUtil;

/**
 *
 */
public abstract class AbstractTextValidator implements TextValidator {

    protected String msgKey;
    
    protected MessageSource getMessageSource(){
        return (MessageSource)ApplicationContextUtil.getBean("messageSource");
    }

    protected String getMsgKey() {
        return msgKey;
    }

    protected void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }
}
