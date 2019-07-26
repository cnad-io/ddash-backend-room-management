package io.cnad.api.roommanagement;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import io.cnad.api.roommanagement.controller.RoomController;
import io.cnad.api.roommanagement.model.Room;
import io.cnad.api.roommanagement.model.UserRoom;

@Path("/api")
public class RoomResource {

	private static final Logger LOGGER = Logger.getLogger(RoomResource.class.getName());

	@Inject
	private RoomController roomController;

	@GET
	@Path("/rooms")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRooms() {
		LOGGER.info("Get all function users requested.");
		List<Room> result = roomController.findRooms();
		LOGGER.debug("Find users executed.");
		LOGGER.trace(result);
		if (result.size() == 0) {
			LOGGER.info("Get all users respond Users not available.");
			return error(Status.NOT_FOUND.getStatusCode(), "Users not available");
		}
		LOGGER.info("Get all users function respond a user list.");
		return Response.ok(result).build();
	}

	@GET
	@Path("/room/{roomId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("roomId") String roomId) {
		LOGGER.info("Get room function requested.");
		LOGGER.trace(roomId);
		Room room = roomController.getRoom(roomId);
		LOGGER.debug("Find room executed.");
		if (room == null) {
			LOGGER.warn("User with id of " + roomId + " does not exist.");
			return error(Status.NOT_FOUND.getStatusCode(), "Room with id of " + roomId + " does not exist.");
		}
		LOGGER.trace(room);
		LOGGER.info("Get room function respond the requested room.");
		return Response.ok(room).build();
	}
	

	@POST
	@Path("/Create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRoom() {
		LOGGER.info("Create user function requested.");

		Room room = new Room(new Date());
		try {
			roomController.createRoom(room);
			LOGGER.debug("Create room executed.");
		} catch (Exception e) {
			LOGGER.error("Create room error: " + e.getMessage());
			return error(Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
		}
		LOGGER.info("Create user function respond the user created.");
		return Response.ok(room).status(201).build();
	}

	@PUT
	@Path("/room/{roomId}/addUser/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@PathParam("userId") String userId,@PathParam("roomId") String roomId) {
		LOGGER.info("Update user function requested.");
		if (userId == null) {
			LOGGER.warn("Update user request with invalid payload!");
			return error(Status.BAD_REQUEST.getStatusCode(), "Invalid payload!");
		}
		try {
			
			roomController.AddUser(new UserRoom(UUID.fromString(roomId), userId));
			LOGGER.debug("Find user executed.");
	
			return Response.ok().status(200).build();
		} catch (Exception e) {
			LOGGER.error("Update user error: " + e.getMessage());
			return error(Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
		}
	}

	@DELETE
	@Path("/room/{roomId}")
	public Response deleteUser(@PathParam("roomId") String roomId) {
		LOGGER.info("Delete user function requested.");
		LOGGER.trace(roomId);
		try {
			Room entity = roomController.getRoom(roomId);
			LOGGER.debug("Find user executed.");
			if (entity == null) {
				LOGGER.warn("Room with id of " + roomId + " does not exist.");
				return error(Status.NOT_FOUND.getStatusCode(), "Room with id of " + roomId + " does not exist.");
			}
			LOGGER.trace(entity);
			roomController.deleteRoom(entity);
			LOGGER.debug("Delete user executed.");
		} catch (Exception e) {
			LOGGER.error("Update room error: " + e.getMessage());
			return error(Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
		}
		LOGGER.info("Delete room function respond no content.");
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/room/{roomId}/removeUser/{userId}")
	public Response removeUser(@PathParam("userId") String userId,@PathParam("roomId") String roomId) {
		LOGGER.info("Delete user function requested.");
		LOGGER.trace(userId);
		try {
			UserRoom entity = roomController.getUserRoom(roomId, userId);

			LOGGER.debug("Find user executed.");
			if (entity == null) {
				LOGGER.warn("User with id of " + userId + " does not exist.");
				return error(Status.NOT_FOUND.getStatusCode(), "User with id of " + userId + " does not exist.");
			}
			LOGGER.trace(entity);
			roomController.removeUser(entity);
			LOGGER.debug("remove user executed.");
		} catch (Exception e) {
			LOGGER.error("Update user error: " + e.getMessage());
			return error(Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
		}
		LOGGER.info("Delete user function respond no content.");
		return Response.noContent().build();
	}

	private Response error(int code, String message) {
		return Response.status(code).entity(Json.createObjectBuilder().add("error", message).add("code", code).build())
				.build();
	}
}