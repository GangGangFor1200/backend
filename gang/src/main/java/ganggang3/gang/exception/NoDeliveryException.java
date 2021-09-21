package ganggang3.gang.exception;

import org.springframework.dao.DataAccessException;

public class NoDeliveryException extends DataAccessException {
    public NoDeliveryException(String msg) {
        super(msg);
    }
}
