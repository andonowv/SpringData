package softuni.exam.models.Dto;

import com.google.gson.annotations.Expose;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;

public class PictureSeedDto {
    @Expose
    private String name;
    @Expose
    private String dataAndTime;
    @Expose
    private Long car;

    public PictureSeedDto() {
    }

    @Size (min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
