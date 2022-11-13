package pl.cd.project.gamesstore.dto.es;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequestDTO {
    private List<String> fields;
    private String searchTerm;
}
