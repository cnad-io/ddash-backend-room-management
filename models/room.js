'use strict'

const Sequelize = require('sequelize')

const sequelize = new Sequelize(
  process.env.DATABASE_NAME || 'roomdb',
  process.env.DATABASE_USERNAME || 'root',
  process.env.DATABASE_PASSWORD || 'password', {
    host: process.env.DATABASE_HOST || 'localhost',
    port: process.env.DATABASE_PORT || 3306,
    dialect: process.env.DATABASE_DIALECT || 'mariadb'
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
  nickname: {
    type: Sequelize.STRING,
    allowNull: false
  },
  score: {
    type: Sequelize.INTEGER
  }
}, {
  sequelize,
  modelName: 'user_room'
})

class Room extends Model {}

Room.init({
  id: {
    type: Sequelize.UUID,
    allowNull: false,
    primaryKey: true,
    defaultValue: Sequelize.UUIDV4
  }
}, {
  sequelize,
  modelName: 'room'
})

User.belongsTo(Room);
Room.hasMany(User, { as: 'users' });

//if (process.env.NODE_ENV !== 'production') {
  sequelize.sync()
//}

module.exports = {
  room: Room,
  user: User
}
