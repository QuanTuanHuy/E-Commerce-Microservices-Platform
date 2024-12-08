package hust.project.ratingservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {

    private Object meta;
    private Object data;

    public Resource(Object data) {
        this.meta = new MetaResource(HttpStatus.OK.value(), "Success");
        this.data = data;
    }

    public Resource(Integer code, String message){
        this.meta = new MetaResource(code, message);
        this.data = null;
    }
}