package es.fandango.data.repository.impl;

import com.mongodb.reactivestreams.client.Success;
import es.fandango.data.model.File;
import es.fandango.data.repository.FileRepository;
import es.fandango.data.config.MongoRepository;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import static com.mongodb.client.model.Filters.eq;

@Slf4j
@Singleton
public class FileRepositoryImpl implements FileRepository {

  /** The mongo repository */
  @Inject
  MongoRepository mongoRepository;

  @Override public Publisher<File> getFile(String fileId) {

    // Build the search filter
    Bson filter = eq(new ObjectId(fileId));

    // Return the file
    return mongoRepository
        .fileCollection()
        .find(filter, File.class)
        .first();
  }

  @Override public String saveFileAndGetId(File file) {

    Success success = Observable.fromPublisher(
        mongoRepository
            .fileCollection()
            .insertOne(file))
        .blockingFirst();

    log.info(success.name() + " saved File with id : " + file.getId().toString());

    return file
        .getId()
        .toString();
  }
}