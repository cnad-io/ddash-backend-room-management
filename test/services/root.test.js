'use strict'

const { test } = require('tap')
const { build } = require('../helper')

test('Default root route Room Management', (t) => {
  t.plan(2)
  const app = build(t)
  var id = '';

  app.inject({
    url: '/api/rooms'
  }, (err, res) => {
    t.error(err)
    t.deepEqual(JSON.parse(res.payload), [])
  })

  app.inject({
    url: '/api/create',
    method: 'POST',
  }, (err, res) => {
    t.error(err)
    id = JSON.parse(res.payload).id
    t.type(JSON.parse(res.payload).id, 'string')
  })

  app.inject({
    url: '/api/create',
    method: 'POST',
  }, (err, res) => {
    t.error(err)
    id = JSON.parse(res.payload).id
    t.type(JSON.parse(res.payload).id, 'string')
  })
})
