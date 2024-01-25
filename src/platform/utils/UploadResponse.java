package platform.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadResponse {
    private String id;

    public UploadResponse(String id) {
        this.id = id;
    }
}
