package platform.model;

import lombok.Data;

@Data
public class SimpleCode {
    String code;

    public String getCode() {
        return code;
    }

    void setCode(String abc) {
        this.code = abc;
    }

    public SimpleCode(String code) {
        this.code = code;
    }

    public SimpleCode() {
    }
}
