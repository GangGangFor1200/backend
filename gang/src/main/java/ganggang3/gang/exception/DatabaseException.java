package ganggang3.gang.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;


public class DatabaseException extends DataAccessException {
    public DatabaseException(String msg) {
        super(msg);
    }
}
