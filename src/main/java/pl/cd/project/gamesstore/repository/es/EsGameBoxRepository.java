package pl.cd.project.gamesstore.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.cd.project.gamesstore.model.esdocuments.EsGameBox;

public interface EsGameBoxRepository extends ElasticsearchRepository<EsGameBox, String> {

}
