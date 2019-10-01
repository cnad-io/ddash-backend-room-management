'use strict'

const { test } = require('tap')
const { build } = require('../helper')

test('Health function', (t) => {
  t.plan(2)
  const app = build(t)

  app.inject({
    url: '/health'
  }, (err, res) => {
    t.error(err)
    t.strictEqual(response.statusCode, 200)
    t.strictEqual(response.headers['content-type'], 'application/json; charset=utf-8')
    t.deepEqual(JSON.parse(res.payload), { message: 'ok' })
  })
})
