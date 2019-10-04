'use strict'
var logger = require('pino')({
  'level': process.env.LOG_LEVEL || 'info'
});

var Room = require('../models/room')

module.exports = function (fastify, opts, next) {
  fastify.get('/api/rooms', function (request, reply) {
    Room.findAll().then(function (rooms) {
      reply
        .code(200)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(rooms)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.get('/api/room/:roomId', function (request, reply) {
    logger.info("requesting room"+ JSON.stringify(request))
    Room.findByPk(request.roomId).then(function (room) {
      reply
        .code(200)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(room)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.get('/api/room/:roomId/users', function (request, reply) {
    logger.info("requesting user in room"+ JSON.stringify(request))

    Room.findByPk(request.roomId).then(function (room) {
      reply
        .code(200)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(room.playerList)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.post('/api/create', function (request, reply) {
    Room.create(request.body).then(function (room) {
      reply
        .code(201)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(room)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.put('/api/room/:roomId/addUser/:userId', function (request, reply) {
    Room.findByPk(request.query.roomId).then(function (room) {
      if (!room.playerList) {
        room.playerList = []
      }
      room.playerList.push({
        id: request.query.userId,
        score: 0
      })
      Room.update(room, {
        where: {
          id: request.query.roomId
        }
      }).then(function (roomUpdated) {
        reply
          .code(200)
          .header('Content-Type', 'application/json; charset=utf-8')
          .send(roomUpdated)
      })
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.delete('/api/room/:roomId/removeUser/:userId', function (request, reply) {
    Room.findByPk(request.query.roomId).then(function (room) {
      room.playerList = room.playerList.filter(function (value) {
        return value.id !== request.query.userId
      })
      Room.update(room, {
        where: {
          id: request.query.roomId
        }
      }).then(function (roomUpdated) {
        reply
          .code(200)
          .header('Content-Type', 'application/json; charset=utf-8')
          .send(roomUpdated)
      })
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  fastify.delete('/api/room/:roomId', function (request, reply) {

    Room.destroy({
      where: {
        id: request.query.roomId
      }
    }).then(function () {
      reply
        .code(204)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  next()
}
