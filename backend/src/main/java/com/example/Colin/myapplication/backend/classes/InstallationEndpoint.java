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
        name = "installationApi",
        version = "v1",
        resource = "installation",
        namespace = @ApiNamespace(
                ownerDomain = "classes.backend.myapplication.Colin.example.com",
                ownerName = "classes.backend.myapplication.Colin.example.com",
                packagePath = ""
        )
)
public class InstallationEndpoint {

    private static final Logger logger = Logger.getLogger(InstallationEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Installation.class);
    }

    /**
     * Returns the {@link Installation} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Installation} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "installation/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Installation get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Installation with ID: " + id);
        Installation installation = ofy().load().type(Installation.class).id(id).now();
        if (installation == null) {
            throw new NotFoundException("Could not find Installation with ID: " + id);
        }
        return installation;
    }

    /**
     * Inserts a new {@code Installation}.
     */
    @ApiMethod(
            name = "insert",
            path = "installation",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Installation insert(Installation installation) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that installation.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(installation).now();
        logger.info("Created Installation with ID: " + installation.getId());

        return ofy().load().entity(installation).now();
    }

    /**
     * Updates an existing {@code Installation}.
     *
     * @param id           the ID of the entity to be updated
     * @param installation the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Installation}
     */
    @ApiMethod(
            name = "update",
            path = "installation/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Installation update(@Named("id") Long id, Installation installation) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(installation).now();
        logger.info("Updated Installation: " + installation);
        return ofy().load().entity(installation).now();
    }

    /**
     * Deletes the specified {@code Installation}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Installation}
     */
    @ApiMethod(
            name = "remove",
            path = "installation/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Installation.class).id(id).now();
        logger.info("Deleted Installation with ID: " + id);
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
            path = "installation",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Installation> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Installation> query = ofy().load().type(Installation.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Installation> queryIterator = query.iterator();
        List<Installation> installationList = new ArrayList<Installation>(limit);
        while (queryIterator.hasNext()) {
            installationList.add(queryIterator.next());
        }
        return CollectionResponse.<Installation>builder().setItems(installationList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Installation.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Installation with ID: " + id);
        }
    }
}