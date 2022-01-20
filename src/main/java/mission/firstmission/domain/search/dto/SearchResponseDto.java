package mission.firstmission.domain.search.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchResponseDto {

    private int total;
    private int start;
    private int display;

    private item[] items;

    static class item {
        public String title;
        public String link;
        public String description;
        public String thumbnail;
    }
}
