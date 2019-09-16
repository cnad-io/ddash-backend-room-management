package io.cnad.api.roommanagement.controller;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import io.cnad.api.roommanagement.model.Room;
import io.cnad.api.roommanagement.model.UserRoom;

@ApplicationScoped
public class RoomController {

	
	private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

	
	
	@PersistenceContext
    EntityManager em;
	
	public List<Room> findRooms() {
		return em.createNamedQuery("Rooms.findAll", Room.class)
				.getResultList();
	}
	
	public Room getRoom(String roomId) {
		LOGGER.info(UUID.fromString(roomId));
		return em.find(Room.class, roomId);
	}
	
	public UserRoom getUserRoom(String roomId, String userId) {
		LOGGER.info("userId -> " +userId);
		LOGGER.info("roomId -> " +roomId);

		return em.find(UserRoom.class,new UserRoom(new Room(roomId,null), userId));
	}
	

	public List<UserRoom> getUsersRoom(String roomId) {
		LOGGER.info("roomId -> " +roomId);

		return em.createNamedQuery("UserRooms.findAllByRoomId", UserRoom.class).setParameter("room", new Room(roomId,null)).getResultList();

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
		em.persist(userRoom);
	}
	
	@Transactional
	public void removeUser(UserRoom userRoom) {
		em.remove(em.contains(userRoom) ? userRoom : em.merge(userRoom));	}
	
	@Transactional
	public void deleteRoom(Room room) {
		em.remove(em.contains(room) ? room : em.merge(room));
	} 
}
