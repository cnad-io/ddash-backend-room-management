'use strict'

module.exports = function (fastify, opts, next) {
  fastify.get('/health', function (request, reply) {
    reply
      .code(200)
      .header('Content-Type', 'application/json; charset=utf-8')
      .send({
        message: 'ok'
      })
  })

  next()
}
