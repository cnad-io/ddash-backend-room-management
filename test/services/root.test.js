'use strict'

const { test } = require('tap')
const { build } = require('../helper')

test('default root route', (t) => {
  t.plan(2)
  const app = build(t)

  app.inject({
    url: '/api/rooms'
  }, (err, res) => {
    t.error(err)
    t.deepEqual(JSON.parse(res.payload), [])
  })
})
