'use strict'

const uuid = require('uuid/v4');
const Sequelize = require('sequelize')

const sequelize = new Sequelize(
  process.env.DATABASE_NAME || 'roomdb',
  process.env.DATABASE_USERNAME || 'root',
  process.env.DATABASE_PASSWORD || 'password', {
    host: process.env.DATABASE_HOST || 'localhost',
    dialect: process.env.DB_DIALECT || 'mariadb'
  })

const Model = Sequelize.Model

class User extends Model {}

User.init({
  id: {
    type: Sequelize.UUID,
    allowNull: false,
    primaryKey: true,
    defaultValue: Sequelize.UUIDV4

  },
  score: {
    type: Sequelize.DATE
  }
}, {
  sequelize,
  modelName: 'users-room'
})

User.b
class Room extends Model {}

Room.init({
  id: {
    type: Sequelize.STRING,
    allowNull: false,
    primaryKey: true
  },
  date: {
    type: Sequelize.DATEONLY
  }
}, {
  sequelize,
  modelName: 'room'
})

Room.hasMany(User, {
  as: 'playersList',
  foreignKey: 'room_id'
})

//if (process.env.NODE_ENV !== 'production') {
  sequelize.sync()
//}

module.exports = Room
