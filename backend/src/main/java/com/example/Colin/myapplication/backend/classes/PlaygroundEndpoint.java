package com.example.Colin.myapplication.backend.classes;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "playgroundApi",
        version = "v1",
        resource = "playground",
        namespace = @ApiNamespace(
                ownerDomain = "classes.backend.myapplication.Colin.example.com",
                ownerName = "classes.backend.myapplication.Colin.example.com",
                packagePath = ""
        )
)
public class PlaygroundEndpoint {

    private static final Logger logger = Logger.getLogger(PlaygroundEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Playground.class);
    }

    /**
     * Returns the {@link Playground} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Playground} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "playground/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Playground get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Playground with ID: " + id);
        Playground playground = ofy().load().type(Playground.class).id(id).now();
        if (playground == null) {
            throw new NotFoundException("Could not find Playground with ID: " + id);
        }
        return playground;
    }

    /**
     * Inserts a new {@code Playground}.
     */
    @ApiMethod(
            name = "insert",
            path = "playground",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Playground insert(Playground playground) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that playground.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(playground).now();
        logger.info("Created Playground with ID: " + playground.getId());

        return ofy().load().entity(playground).now();
    }

    /**
     * Updates an existing {@code Playground}.
     *
     * @param id         the ID of the entity to be updated
     * @param playground the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Playground}
     */
    @ApiMethod(
            name = "update",
            path = "playground/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Playground update(@Named("id") Long id, Playground playground) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(playground).now();
        logger.info("Updated Playground: " + playground);
        return ofy().load().entity(playground).now();
    }

    /**
     * Deletes the specified {@code Playground}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Playground}
     */
    @ApiMethod(
            name = "remove",
            path = "playground/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Playground.class).id(id).now();
        logger.info("Deleted Playground with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "playground",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Playground> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Playground> query = ofy().load().type(Playground.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Playground> queryIterator = query.iterator();
        List<Playground> playgroundList = new ArrayList<Playground>(limit);
        while (queryIterator.hasNext()) {
            playgroundList.add(queryIterator.next());
        }
        return CollectionResponse.<Playground>builder().setItems(playgroundList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Playground.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Playground with ID: " + id);
        }
    }
}