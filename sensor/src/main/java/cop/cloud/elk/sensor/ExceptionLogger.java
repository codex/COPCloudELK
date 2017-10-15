package cop.cloud.elk.sensor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionLogger {
	Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public void defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error(req.getRemoteUser(), e);
	}

}
