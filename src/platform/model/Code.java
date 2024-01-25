package platform.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Code {
    private String code;
    private LocalDate date;
    static private int count = 0;
    private int ID;

    public Code(String code) {
        this.code = code;
        this.date = LocalDate.now();
        this.ID = ++this.count;
    }

    public String getCode() {
        return code;
    }
    public LocalDate getDate() {
        return date;
    }

    public int HereIsTheID() {
        return ID;
    }

    public void setCode(String code) {
        this.code = code;
        this.date = LocalDate.now();
    }
}
