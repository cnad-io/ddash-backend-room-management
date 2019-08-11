package io.cnad.api.roommanagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.cnad.api.roommanagement.model.Room;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@Tag("unit")
@DisplayName("User API Tests")
public class RoomResourceTest {

	@Test
	@Tag("get-all-rooms")
	@DisplayName("When is requested all users should return a list of 4 users")
	public void shouldReturnListRooms() throws JsonProcessingException {
		Room[] rooms = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get("/api/rooms").then().assertThat().statusCode(200).extract().as(Room[].class);
		assertThat(rooms.length, not(0));
	}

	@Test
	@Tag("get-room")
	@DisplayName("When is requested an user by id should return an error message user not found if that user not exists")
	public void shouldReturnRoomNotFound() {
		given().when().get("/api/room/61336330-6135-6665-2d61-6436612d3139").then().assertThat().statusCode(404)
				.body("error", equalTo("Room with id of 61336330-6135-6665-2d61-6436612d3139 does not exist."));
	}

	@Test
	@Tag("create-room")
	@DisplayName("When is requested an user creation should return an error saying the username is required to create an user if the username wasn't sent")
	public void ReturnCreatedRoom() {
		given().contentType(ContentType.JSON).accept(ContentType.JSON).when().post("/api/create")
				.then().assertThat().statusCode(201);
	}

	@Test
	@Tag("add-user-room")
	@DisplayName("When is requested an user creation should return an error saying the username is required to create an user if the username wasn't sent")
	public void ReturnAddUserToRoom() {
		given().contentType(ContentType.JSON).accept(ContentType.JSON).when().put("/api/room/61336330-6135-6665-2d61-8273636621/addUser/61336331-6138-3464-2d61-6436612d3131")
				.then().assertThat().statusCode(200);
	}
	
	@Test
	@Tag("delete-room")
	@DisplayName("When is requested a room delete should return an error message user not found if that user not exists")
	public void shouldReturnRoomNotFoundToDelete() {
		given().when().delete("/api/room/f7ed745e-09c6-40ad-824e-082dbbfc1355").then().assertThat().statusCode(404)
		.body("error", equalTo("Room with id of f7ed745e-09c6-40ad-824e-082dbbfc1355 does not exist."));
	}

	
	@Test
	@Tag("delete-room")
	@DisplayName("When is requested a room delete should return a confimation code")
	public void shouldDeleteRoom() {
		given().contentType(ContentType.JSON).accept(ContentType.JSON).when().delete("/api/room/55f5e1fa-f919-461f-9e43-d6975b001699").then().assertThat().statusCode(204);
	}
	
	
	@Test
	@Tag("remove-user-room")
	@DisplayName("When is requested a user remove from room should return a confimation code")
	public void shouldReturnUserNotFoundToRemove() {
		given().when().delete("/api/room/61336330-6135-7827-2d61-8273636621/removeUser/61336332-3366-6164-2d61-6436612d3131").then().assertThat().statusCode(404)
		.body("error", equalTo("User with id of 61336332-3366-6164-2d61-6436612d3131 does not exist in this room."));
	}
	
	@Test
	@Tag("remove-user-room")
	@DisplayName("When is requested a user remove from room should return a confimation code")
	public void shouldRemoveUserFromRoom() {
		given().when().delete("/api/room/61336330-6135-7765-2d61-6436612d3139/removeUser/61336330-6135-6665-2d61-6436612d3131").then().assertThat().statusCode(204);
	}
	
}
