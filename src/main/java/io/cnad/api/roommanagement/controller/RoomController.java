package io.cnad.api.roommanagement.controller;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.cnad.api.roommanagement.model.Room;
import io.cnad.api.roommanagement.model.UserRoom;

@ApplicationScoped
public class RoomController {

	@PersistenceContext
    EntityManager em;
	
	public List<Room> findRooms() {
		return em.createNamedQuery("Rooms.findAll", Room.class)
				.getResultList();
	}
	
	public Room getRoom(String roomId) {
		return em.find(Room.class, UUID.fromString(roomId));
	}
	
	public UserRoom getUserRoom(String roomId, String userId) {
		return em.find(UserRoom.class,new UserRoom(UUID.fromString(roomId), userId));
	}
	

	@Transactional
	public void createRoom(Room room) {
		em.persist(room);
	}
	
	@Transactional
	public Room updateRoom(Room room) {
		return em.merge(room);
	}
	
	@Transactional
	public void AddUser(UserRoom userRoom) {
		em.merge(userRoom);
	}
	
	@Transactional
	public void removeUser(UserRoom userRoom) {
		em.remove(em.contains(userRoom) ? userRoom : em.merge(userRoom));	}
	
	@Transactional
	public void deleteRoom(Room room) {
		em.remove(em.contains(room) ? room : em.merge(room));
	} 
}
