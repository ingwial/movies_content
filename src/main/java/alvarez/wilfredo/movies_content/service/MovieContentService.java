package alvarez.wilfredo.movies_content.service;

import alvarez.wilfredo.movies_content.service.contrat.MovieContentTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieContentService {
    Mono<List<MovieContentTO>> getAll();
    Mono<MovieContentTO> getById(String id );
    Mono<MovieContentTO> create( MovieContentTO movieContentTO );
    Mono<MovieContentTO> update( String id, MovieContentTO movieContentTO );
    Mono<Integer> delete( String id );
}
