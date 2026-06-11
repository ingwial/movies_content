package alvarez.wilfredo.movies_content.service.datasource;

import com.contentful.java.cma.model.CMAArray;
import com.contentful.java.cma.model.CMAEntry;
import reactor.core.publisher.Mono;

public interface MovieContentRepository {
    Mono<CMAArray<CMAEntry>> getAll();
    Mono<CMAEntry> getById(String id );
    Mono<CMAEntry> create( String type, CMAEntry entry );
    Mono<CMAEntry> update( CMAEntry entry );
    Mono<Integer> delete(CMAEntry entry);
}
