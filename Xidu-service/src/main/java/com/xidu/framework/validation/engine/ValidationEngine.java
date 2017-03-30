package com.xidu.framework.validation.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.validation.validator.TextValidator;

/**
 *
 */
public class ValidationEngine {

    private Locale locale;
    private Map<String, List<TextValidator>> validationMap = new HashMap<String, List<TextValidator>>();
    private Map<String, String> valueMap = new HashMap<String, String>(); 
    
    public ValidationEngine(){
        this.locale = Locale.ENGLISH;
    }
    public ValidationEngine(Locale locale){
        this.locale = locale;
    }
    
    public void addValidator(String property,String text, TextValidator... validators){
        if(StringUtils.isBlank(property)){
            return;
        }
        
        if(!valueMap.containsKey(property)){
            valueMap.put(property, text);
        }
        
        List<TextValidator> textValidators = validationMap.get(property);
        if(null == textValidators){
            textValidators = new LinkedList<TextValidator>();
            
        }
        
        if(null != validators){
            for(TextValidator textValidator : validators){
                textValidators.add(textValidator);
            }
        }
        validationMap.put(property, textValidators);
    }
    
    
    public Map<String, String> run(){
        
        Map<String, String> validationInfoMap = new HashMap<String, String>();
        
        for(Map.Entry<String, String> valueEntry : valueMap.entrySet()){
            
            String text = valueEntry.getValue();
            List<TextValidator> textValidators = validationMap.get(valueEntry.getKey());
            StringBuilder sb = new StringBuilder();
            if(null != textValidators){
                for(TextValidator textValidator : textValidators){
                    String msg = textValidator.validate(text, locale);
                    if(StringUtils.isNotBlank(msg)){
                        sb.append(msg).append(",");
                    }
                }
            }
            if(sb.length() > 0){
                validationInfoMap.put(valueEntry.getKey(), sb.substring(0,sb.length()-1));
            }
            
        }
        return validationInfoMap;
        
    }
}
