package ru.project.otus.gateservice.log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("compositeCode")
    @Expose
    private String compositeCode;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("detailMessage")
    @Expose
    private String detailMessage;

    public Error(ErrorDetail detail, String detailMessage) {
        this.compositeCode = null;

        switch (detail) {
            case INNER_ERROR -> {
                this.code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
                this.message = "Inner error";
            }
            case PARSE_ERROR -> {
                this.code = HttpStatus.SC_BAD_REQUEST;
                this.message = "Parse error";
            }
            case EXTERNAL_ERROR -> {
                this.code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
                this.message = "Internal service error";
            }
            default -> {
                this.code = HttpStatus.SC_BAD_REQUEST;
                this.message = "Unknown error";
            }
        }
        this.detailMessage = detailMessage;
    }
}
