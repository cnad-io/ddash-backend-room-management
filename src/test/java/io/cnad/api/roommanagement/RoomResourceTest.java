package io.cnad.api.roommanagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
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
		Room[] users = given().when().get("/api/rooms").then().assertThat().statusCode(200).extract().as(Room[].class);
		assertThat(users.length, equalTo(4));
	}

	@Test
	@Tag("get-room")
	@DisplayName("When is requested an user by id should return an error message user not found if that user not exists")
	public void shouldReturnUserNotFound() {
		given().when().get("/api/room/61336330-6135-6665-2d61-6436612d3139").then().assertThat().statusCode(404)
				.body("error", equalTo("Room with id of 61336330-6135-6665-2d61-6436612d3139 does not exist."));
	}

	@Test
	@Tag("create-room")
	@DisplayName("When is requested an user creation should return an error saying the username is required to create an user if the username wasn't sent")
	public void ReturnCreatedRoom() {
		given().when().post("/api/create")
				.then().assertThat().statusCode(201);
	}

	@Test
	@Tag("add-user-room")
	@DisplayName("When is requested an user creation should return an error saying the username is required to create an user if the username wasn't sent")
	public void ReturnAddUserToRoom() {
		given().when().put("/room/61336330-6135-6665-2d61-8273636621/addUser/61336332-3366-6164-2d61-6436612d3131")
				.then().assertThat().statusCode(200);
	}
	
	@Test
	@Tag("delete-room")
	@DisplayName("When is requested a room delete should return an error message user not found if that user not exists")
	public void shouldReturnRoomNotFoundToDelete() {
		given().when().delete("/room/61336330-6135-6665-2d61-8273636621").then().assertThat().statusCode(404)
		.body("error", equalTo("Room with id of 61336330-6135-6665-2d61-8273636621 does not exist."));
	}

	
	@Test
	@Tag("delete-room")
	@DisplayName("When is requested a room delete should return a confimation code")
	public void shouldDeleteRoom() {
		given().when().delete("/room/61336330-6135-6665-2d61-72636372829").then().assertThat().statusCode(204);
	}
	
	
	@Test
	@Tag("remove-user-room")
	@DisplayName("When is requested a user remove from room should return a confimation code")
	public void shouldReturnUserNotFoundToRemove() {
		given().when().delete("/room/61336330-6135-7827-2d61-8273636621/removeUser/61336332-3366-6164-2d61-6436612d3131").then().assertThat().statusCode(404)
		.body("error", equalTo("User with id of 61336332-3366-6164-2d61-6436612d3131 does not exist in this room."));
	}
	
	@Test
	@Tag("remove-user-room")
	@DisplayName("When is requested a user remove from room should return a confimation code")
	public void shouldRemoveUserFromRoom() {
		given().when().delete("/room/61336330-6135-2778-2d61-8273636621/removeUser/61336332-3366-6164-2d61-6436612d3131").then().assertThat().statusCode(204);
	}
	
}
