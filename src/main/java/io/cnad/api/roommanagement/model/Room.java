package io.cnad.api.roommanagement.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "date" })
@NamedQueries({ 
	@NamedQuery(name = "Rooms.findAll", query = "SELECT f FROM Room f")
})
public class Room {


	
	
	@Id
	@JsonProperty("id")	
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	

	@JsonProperty("date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date date;

//    @OneToMany(mappedBy ="room" , cascade = CascadeType.ALL )
//    @JsonManagedReference
//    @JsonIgnore
//    private Set<UserRoom> users;
//    
//    
//    
//	public Set<UserRoom> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserRoom> users) {
//		this.users = users;
//	}

	public Room() {
		super();
	}

	public Room(Date date) {
		super();
		this.date = date;
	}
	
	public Room(String id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Room other = (Room) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
