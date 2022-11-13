package pl.cd.project.gamesstore.model.esdocuments;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import pl.cd.project.gamesstore.helper.IndicesEs;

@Data
@Document(indexName = IndicesEs.GAMEBOX_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class EsGameBox {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

}
