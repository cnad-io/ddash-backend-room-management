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
	@NamedQuery(name = "UserRooms.findAll", query = "SELECT f FROM UserRoom f")
})
public class UserRoom implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonBackReference
	private Room room;
	
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference
	private User user;

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

	
	public UserRoom(Room room, User user) {
		super();
		this.user = user;
		this.room = room;
	}
	@JsonProperty("roomid")
	public Room getRoom() {
		return room;
	}
	
	
	@JsonProperty("userid")
	public User getUser() {
		return user;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UsersRoom [room=" + room + ", user=" + user + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
