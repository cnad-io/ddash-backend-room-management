'use strict'

const logger = require('pino')({
  'level': process.env.LOG_LEVEL || 'info'
});

var model = require('../models/room')

module.exports = function (fastify, opts, next) {
  fastify.get('/api/rooms', function (request, reply) {
    model.room.findAll().then(function (rooms) {
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
    logger.debug("Requesting room", request.params.roomId)
    if (!request.params.roomId) {
      reply
        .code(400)
    } else {
      model.room.findByPk(request.params.roomId, {
        include: [
          { model: model.user, as: 'users' }
        ]
      }).then(function (room) {
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
    }
  })

  fastify.get('/api/room/:roomId/users', function (request, reply) {
    logger.debug("Requesting user in room", request.params.roomId)
    if (!request.params.roomId) {
      reply
        .code(400)
    } else {
      model.user.findAll({
        where: {
          roomId: request.params.roomId
        }
      }).then(function (users) {
        reply
          .code(200)
          .header('Content-Type', 'application/json; charset=utf-8')
          .send(users);
      }).catch(function (error) {
        reply
          .code(500)
          .header('Content-Type', 'application/json; charset=utf-8')
          .send(error)
      })
    }
  })

  fastify.post('/api/create', function (request, reply) {
    model.room.create({}).then(function (room) {
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
    model.user.create({
      username: request.params.userId,
      score: 0,
      roomId: request.params.roomId
    }).then(function (user) {
      reply
        .code(200)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(user)
    }).catch(function (error) {
      reply
        .code(500)
        .header('Content-Type', 'application/json; charset=utf-8')
        .send(error)
    })
  })

  next()
}
