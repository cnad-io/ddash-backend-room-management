package io.cnad.api.roommanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "roomId", "userId" , "score" })
@NamedQueries({ 
	@NamedQuery(name = "UserRooms.findAll", query = "SELECT f FROM UserRoom f"),
	@NamedQuery(name = "UserRooms.findAllByRoomId", query = "SELECT f FROM UserRoom f WHERE f.room = :room") 
})
public class UserRoom implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    @JsonBackReference
	private Room room;
	
    @Id
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

	
	public UserRoom(Room room, String userId) {
		super();
		this.userId = userId;
		this.room = room;
	}
	@JsonProperty("roomId")
	public Room getRoom() {
		return room;
	}
	
	
	@JsonProperty("userId")
	public String getUserId() {
		return userId;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setUser(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UsersRoom [room=" + room + ", userId=" + userId + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
