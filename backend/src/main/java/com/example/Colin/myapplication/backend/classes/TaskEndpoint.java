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
        name = "taskApi",
        version = "v1",
        resource = "task",
        namespace = @ApiNamespace(
                ownerDomain = "classes.backend.myapplication.Colin.example.com",
                ownerName = "classes.backend.myapplication.Colin.example.com",
                packagePath = ""
        )
)
public class TaskEndpoint {

    private static final Logger logger = Logger.getLogger(TaskEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Task.class);
    }

    /**
     * Returns the {@link Task} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Task} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "task/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Task get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Task with ID: " + id);
        Task task = ofy().load().type(Task.class).id(id).now();
        if (task == null) {
            throw new NotFoundException("Could not find Task with ID: " + id);
        }
        return task;
    }

    /**
     * Inserts a new {@code Task}.
     */
    @ApiMethod(
            name = "insert",
            path = "task",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Task insert(Task task) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that task.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(task).now();
        logger.info("Created Task with ID: " + task.getId());

        return ofy().load().entity(task).now();
    }

    /**
     * Updates an existing {@code Task}.
     *
     * @param id   the ID of the entity to be updated
     * @param task the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Task}
     */
    @ApiMethod(
            name = "update",
            path = "task/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Task update(@Named("id") Long id, Task task) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(task).now();
        logger.info("Updated Task: " + task);
        return ofy().load().entity(task).now();
    }

    /**
     * Deletes the specified {@code Task}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Task}
     */
    @ApiMethod(
            name = "remove",
            path = "task/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Task.class).id(id).now();
        logger.info("Deleted Task with ID: " + id);
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
            path = "task",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Task> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Task> query = ofy().load().type(Task.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Task> queryIterator = query.iterator();
        List<Task> taskList = new ArrayList<Task>(limit);
        while (queryIterator.hasNext()) {
            taskList.add(queryIterator.next());
        }
        return CollectionResponse.<Task>builder().setItems(taskList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Task.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Task with ID: " + id);
        }
    }
}