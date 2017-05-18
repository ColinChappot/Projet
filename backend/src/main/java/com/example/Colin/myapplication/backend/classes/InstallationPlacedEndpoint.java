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
        name = "installationPlacedApi",
        version = "v1",
        resource = "installationPlaced",
        namespace = @ApiNamespace(
                ownerDomain = "classes.backend.myapplication.Colin.example.com",
                ownerName = "classes.backend.myapplication.Colin.example.com",
                packagePath = ""
        )
)
public class InstallationPlacedEndpoint {

    private static final Logger logger = Logger.getLogger(InstallationPlacedEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(InstallationPlaced.class);
    }

    /**
     * Returns the {@link InstallationPlaced} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code InstallationPlaced} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "installationPlaced/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public InstallationPlaced get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting InstallationPlaced with ID: " + id);
        InstallationPlaced installationPlaced = ofy().load().type(InstallationPlaced.class).id(id).now();
        if (installationPlaced == null) {
            throw new NotFoundException("Could not find InstallationPlaced with ID: " + id);
        }
        return installationPlaced;
    }

    /**
     * Inserts a new {@code InstallationPlaced}.
     */
    @ApiMethod(
            name = "insert",
            path = "installationPlaced",
            httpMethod = ApiMethod.HttpMethod.POST)
    public InstallationPlaced insert(InstallationPlaced installationPlaced) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that installationPlaced.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(installationPlaced).now();
        logger.info("Created InstallationPlaced with ID: " + installationPlaced.getId());

        return ofy().load().entity(installationPlaced).now();
    }

    /**
     * Updates an existing {@code InstallationPlaced}.
     *
     * @param id                 the ID of the entity to be updated
     * @param installationPlaced the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code InstallationPlaced}
     */
    @ApiMethod(
            name = "update",
            path = "installationPlaced/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public InstallationPlaced update(@Named("id") Long id, InstallationPlaced installationPlaced) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(installationPlaced).now();
        logger.info("Updated InstallationPlaced: " + installationPlaced);
        return ofy().load().entity(installationPlaced).now();
    }

    /**
     * Deletes the specified {@code InstallationPlaced}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code InstallationPlaced}
     */
    @ApiMethod(
            name = "remove",
            path = "installationPlaced/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(InstallationPlaced.class).id(id).now();
        logger.info("Deleted InstallationPlaced with ID: " + id);
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
            path = "installationPlaced",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<InstallationPlaced> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<InstallationPlaced> query = ofy().load().type(InstallationPlaced.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<InstallationPlaced> queryIterator = query.iterator();
        List<InstallationPlaced> installationPlacedList = new ArrayList<InstallationPlaced>(limit);
        while (queryIterator.hasNext()) {
            installationPlacedList.add(queryIterator.next());
        }
        return CollectionResponse.<InstallationPlaced>builder().setItems(installationPlacedList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(InstallationPlaced.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find InstallationPlaced with ID: " + id);
        }
    }
}