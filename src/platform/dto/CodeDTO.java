package platform.dto;

public class CodeDTO implements Comparable<CodeDTO> {
    public CodeDTO() {
    }

    public CodeDTO(String code, String date) {
        this.code = code;
        this.date = date;
    }

    private String code;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeDTO{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(CodeDTO dto) {
        return this.date.compareTo(dto.date);
    }
}
