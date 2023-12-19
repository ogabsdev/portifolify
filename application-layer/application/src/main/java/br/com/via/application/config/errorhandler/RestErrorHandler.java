package br.com.via.application.config.errorhandler;

import br.com.via.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static final String URL_STATUS_CODE_DOC = "https://httpwg.org/specs/rfc9110.html";

    private static final String UNEXPECTED_ERROR_MSG = "An unexpected error has occurred";

    private static final String ERROR_TRACE_PROPERTY = "errorTrace";

    private static final String INVALID_PARAMS_PROPERTY = "invalidParams";

    @ExceptionHandler({DomainException.class})
    public final ProblemDetail handleDomainException(DomainException ex, WebRequest request) {
        var errorTrace = UUID.randomUUID().toString();
        log.error("DomainException. Trace: " + errorTrace, ex);

        ProblemDetail body = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()),
                ex.getLocalizedMessage()
        );

        body.setType(URI.create(URL_STATUS_CODE_DOC));
        body.setTitle("Your request caused a business error");
        body.setProperty(ERROR_TRACE_PROPERTY, errorTrace);

        return body;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        var errorTrace = UUID.randomUUID().toString();
        log.error("MethodArgumentNotValid. Trace: " + errorTrace, ex);

        List<Map<String, String>> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map.Entry<String, String> fieldName = Map.entry("name", fieldError.getField());
            Map.Entry<String, String> fieldReason = Map.entry("reason", fieldError.getDefaultMessage());

            errors.add(Map.ofEntries(fieldName, fieldReason));
        });

        ProblemDetail body = ProblemDetail.forStatus(
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));

        body.setType(URI.create(URL_STATUS_CODE_DOC));
        body.setProperty(ERROR_TRACE_PROPERTY, errorTrace);
        body.setProperty(INVALID_PARAMS_PROPERTY, errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public final ProblemDetail handleGenericException(Exception ex, WebRequest request) {
        var errorTrace = UUID.randomUUID().toString();
        log.error("Exception. Trace: " + errorTrace, ex);

        ProblemDetail body = ProblemDetail.forStatus(
                HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        body.setType(URI.create(URL_STATUS_CODE_DOC));
        body.setTitle(UNEXPECTED_ERROR_MSG);
        body.setProperty(ERROR_TRACE_PROPERTY, errorTrace);

        return body;
    }

}
