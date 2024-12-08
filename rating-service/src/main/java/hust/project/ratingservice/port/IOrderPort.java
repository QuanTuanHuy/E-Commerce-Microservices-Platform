package hust.project.ratingservice.port;

import java.util.List;

public interface IOrderPort {
    boolean checkOrderExistedByUserId(List<Long> productIds);
}
