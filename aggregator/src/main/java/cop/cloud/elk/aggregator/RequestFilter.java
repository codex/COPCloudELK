package cop.cloud.elk.aggregator;

import static java.time.Instant.now;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.logstash.logback.argument.StructuredArguments;

@Component
public class RequestFilter extends OncePerRequestFilter {
	Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	static final String SESSION_ID = "SessionId";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		addMDCValues(request);
		Instant startTime = now();

		filterChain.doFilter(request, response);

		logger.info("Request took {} ms",
				StructuredArguments.value("DurationMs", Duration.between(startTime, now()).toMillis()));
		removeMDCValues();
	}

	private void removeMDCValues() {
		// TODO remove Session ID to MDC
		MDC.remove("Session_RemoteAddr");
		MDC.remove("Session_RemoteHost");
		MDC.remove("Session_URI");
		MDC.remove("Session_URL");
	}

	private void addMDCValues(HttpServletRequest request) {
		// TODO add Session ID to MDC; get it from headers or create a new ID
		MDC.put("Session_RemoteAddr", request.getRemoteAddr());
		MDC.put("Session_RemoteHost", request.getRemoteHost());
		MDC.put("Session_URI", request.getRequestURI());
		MDC.put("Session_URL", request.getRequestURL().toString());
	}

}