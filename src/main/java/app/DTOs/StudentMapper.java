package app.DTOs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import springfox.documentation.swagger2.mappers.ModelMapper;

public class StudentMapper {


    ModelMapper modelMapper;


    public StudentMapper(ModelMapper modelMapper) {
        modelMapper = modelMapper;
    }
}
