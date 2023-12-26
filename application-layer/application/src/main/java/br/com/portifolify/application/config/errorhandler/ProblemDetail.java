package br.com.portifolify.application.config.errorhandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ProblemDetail {

    private static final URI BLANK_TYPE = URI.create("about:blank");

    @Getter
    private URI type = BLANK_TYPE;

    @Nullable
    private String title;

    @Getter
    @Setter
    private int status;

    @Nullable
    private String detail;

    @Nullable
    private URI instance;

    @Nullable
    private Map<String, Object> properties;

    protected ProblemDetail(int rawStatusCode) {
        this.status = rawStatusCode;
    }

    protected ProblemDetail(ProblemDetail other) {
        this.type = other.type;
        this.title = other.title;
        this.status = other.status;
        this.detail = other.detail;
        this.instance = other.instance;
        this.properties = (other.properties != null ? new LinkedHashMap<>(other.properties) : null);
    }

    protected ProblemDetail() {
    }

    public void setType(URI type) {
        Assert.notNull(type, "'type' is required");
        this.type = type;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getTitle() {
        if (this.title == null) {
            HttpStatus httpStatus = HttpStatus.resolve(this.status);
            if (httpStatus != null) {
                return httpStatus.getReasonPhrase();
            }
        }
        return this.title;
    }

    public void setStatus(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }

    public void setDetail(@Nullable String detail) {
        this.detail = detail;
    }

    @Nullable
    public String getDetail() {
        return this.detail;
    }

    public void setInstance(@Nullable URI instance) {
        this.instance = instance;
    }

    @Nullable
    public URI getInstance() {
        return this.instance;
    }

    public void setProperty(String name, @Nullable Object value) {
        this.properties = (this.properties != null ? this.properties : new LinkedHashMap<>());
        this.properties.put(name, value);
    }

    public void setProperties(@Nullable Map<String, Object> properties) {
        this.properties = properties;
    }

    @Nullable
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return (this == other || (other instanceof ProblemDetail that &&
                getType().equals(that.getType()) &&
                ObjectUtils.nullSafeEquals(getTitle(), that.getTitle()) &&
                this.status == that.status &&
                ObjectUtils.nullSafeEquals(this.detail, that.detail) &&
                ObjectUtils.nullSafeEquals(this.instance, that.instance) &&
                ObjectUtils.nullSafeEquals(this.properties, that.properties)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, getTitle(), this.status, this.detail,
                this.instance, this.properties);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + initToStringContent() + "]";
    }

    protected String initToStringContent() {
        return "type='" + getType() + "'" +
                ", title='" + getTitle() + "'" +
                ", status=" + getStatus() +
                ", detail='" + getDetail() + "'" +
                ", instance='" + getInstance() + "'" +
                ", properties='" + getProperties() + "'";
    }


    public static ProblemDetail forStatus(HttpStatus status) {
        Assert.notNull(status, "HttpStatusCode is required");
        return forStatus(status.value());
    }

    public static ProblemDetail forStatus(int status) {
        return new ProblemDetail(status);
    }

    public static ProblemDetail forStatusAndDetail(HttpStatus status, String detail) {
        Assert.notNull(status, "HttpStatusCode is required");
        ProblemDetail problemDetail = forStatus(status.value());
        problemDetail.setDetail(detail);
        return problemDetail;
    }

}
