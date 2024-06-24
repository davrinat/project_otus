package ru.project.otus.gateservice.log;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.Setter;
import ru.project.otus.gateservice.common.DateAdapter;

import java.io.Serial;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Setter
@Getter
public class LogRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = 1111111111111111111L;

    private String traceId;
    private String spanId;
    private String system;
    private String host;
    private String component;
    private String operation;
    private String direction;
    private String step;
    @JsonAdapter(DateAdapter.class)
    private Date timestamp;
    private Object data;

    public LogRecord() {
        try {
            this.host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            this.host = "unknown";
        }
    }
}
