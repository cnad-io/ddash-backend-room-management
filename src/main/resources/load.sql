INSERT INTO User(id, username) VALUES ('61336330-6135-6665-2d61-6436612d3131', 'camedeir');
INSERT INTO User(id, username) VALUES ('61336331-3166-3663-2d61-6436612d3131', 'gschmidt');
INSERT INTO User(id, username) VALUES ('61336331-6138-3464-2d61-6436612d3131', 'mcaballol');
INSERT INTO User(id, username) VALUES ('61336332-3366-6164-2d61-6436612d3131', 'scanales');

INSERT INTO Room(id, date) VALUES ('61336330-6135-8872-2d61-8836612d3139', null);
INSERT INTO Room(id, date) VALUES ('26816705-b22e-4e08-943a-e1ec06ba4d06', null);
--ReturnAddUserToRoom
INSERT INTO Room(id, date) VALUES ('61336330-6135-6665-2d61-8273636621', null);
--shouldDeleteRoom
INSERT INTO Room(id, date) VALUES ('55f5e1fa-f919-461f-9e43-d6975b001699', null);
--shouldRemoveUserFromRoom
INSERT INTO Room(id, date) VALUES ('61336330-6135-7765-2d61-6436612d3139', null);
INSERT INTO UserRoom(room_id,user_id, score) values ('61336330-6135-7765-2d61-6436612d3139','61336330-6135-6665-2d61-6436612d3131',0);