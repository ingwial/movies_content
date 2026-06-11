package alvarez.wilfredo.movies_content.service.contrat;

import com.contentful.java.cma.model.CMAArray;
import com.contentful.java.cma.model.CMAEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MovieContentBinder {
    MovieContentBinder MOVIE_CONTENT_BINDER = Mappers.getMapper( MovieContentBinder.class );

    default List<MovieContentTO> bind(CMAArray<CMAEntry> cmaEntryCMAArray) {
        if (cmaEntryCMAArray == null) {
            return new ArrayList<>();
        }
        List<MovieContentTO> movieContentTOArrayList = new ArrayList<>();
        cmaEntryCMAArray.getItems().forEach(entry -> movieContentTOArrayList.add(bind(entry)));
        return movieContentTOArrayList;
    }

    default MovieContentTO bind(CMAEntry entry) {
        if (entry == null) {
            return null;
        }
        var movie = new MovieContentTO();
        movie.setId(entry.getId());
        movie.setTitle(entry.getField("title", "en-US"));
        movie.setDescription(entry.getField("description", "en-US"));
        return movie;
    }

    default CMAEntry bind(CMAEntry entry, MovieContentTO movieContentTO) {
        entry.setField("title", "en-US", null != movieContentTO.getTitle() ? movieContentTO.getTitle() : entry.getField("title", "en-US"));
        entry.setField("description", "en-US", null != movieContentTO.getDescription() ? movieContentTO.getDescription() : entry.getField("description", "en-US"));
        return entry;
    }

    default CMAEntry bind(MovieContentTO movieContentTO) {
        return this.bind(new CMAEntry(), movieContentTO);
    }
}
