package spring.boot.endterm.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

    private static ModelMapper modelMapper;

    @Autowired
    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public static<T> T convertToDTO(Object obj, Class<T> tClass) {
        return  modelMapper.map(obj, tClass);
    }

}
