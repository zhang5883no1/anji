package com.xidu.framework.excel.dto;

import java.util.Map;

import com.xidu.framework.excel.validator.ExcelDtoValidator;

public class SampleExcelDto implements ExcelDtoValidator{
    private Long id;
    private String name;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public Map<String,String> validate() {
        
        
//        List<String> validateList = new LinkedList<String>();
//        
//        ValidationEngine engine = new ValidationEngine();
//        engine.addValidator("name", this.name
//            , new RequiredTextValidator("NotEmpty.createDealerDto.name")
//         ,new LengthTextValidator("dto.validator.length", 0, 4));
//        validateList.addAll(engine.run());
        return null;
    }
    
    
}
