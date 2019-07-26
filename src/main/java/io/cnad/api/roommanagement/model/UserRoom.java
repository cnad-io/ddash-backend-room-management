package io.cnad.api.roommanagement.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "roomId", "userId" })
@NamedQueries({ 
	@NamedQuery(name = "UserRooms.findAll", query = "SELECT f FROM UserRoom f")
})
public class UserRoom {

	@Id
	@JsonProperty("roomId")

	
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "roomId", updatable = false, nullable = false)
	private UUID roomId;
	
	@Id
	@JsonProperty("userId")
	private String userId;

	private long score;
	
	@JsonProperty("score")
	public long getScore() {
		return score;
	}

	@JsonProperty("score")
	public void setScore(long score) {
		this.score = score;
	}


	public UserRoom() {
		super();
	}

	
	public UserRoom(UUID roomId, String userId) {
		super();
		this.roomId = roomId;
		this.userId = userId;
	}

	@JsonProperty("roomId")
	public UUID getRoomId() {
		return roomId;
	}

	@JsonProperty("roomId")
	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}

	@JsonProperty("userId")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("userId")
	public void setRoomId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "UsersRoom [roomId=" + roomId + ", userId=" + userId + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoom other = (UserRoom) obj;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
