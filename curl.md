#### get All Users
`curl -s http://localhost:8080/lunch-voting/rest/admin/users --user admin@gmail.com:admin`

#### get User
`curl -s http://localhost:8080/lunch-voting/rest/admin/users/100001 --user admin@gmail.com:admin`

#### delete User
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/admin/users/100000 --user admin@gmail.com:admin`

#### create User
`curl -s -X POST -d '{"name":"NewName", "email":"newmail@gmail.com", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/admin/users --user admin@gmail.com:admin`

#### update User
`curl -s -X PUT -d '{"name":"UpdatedName", "email":"updatedmail@gmail.com", "password":"updatedPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/admin/users/100000 --user admin@gmail.com:admin`

#### get User by Email
`curl -s http://localhost:8080/lunch-voting/rest/admin/users/by?email=user@yandex.ru --user admin@gmail.com:admin`

#### get AuthorizedUser
`curl -s http://localhost:8080/lunch-voting/rest/profile --user admin@gmail.com:admin`

#### delete AuthorizedUser
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/profile --user user@yandex.ru:password`

#### register new User
`curl -s -X POST -d '{"name":"NewName", "email":"newmail@gmail.com", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/profile/register`

#### update AuthorizedUser
`curl -s -X PUT -d '{"name":"UpdatedName", "email":"updatedmail@gmail.com", "password":"updatedPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/profile --user user@yandex.ru:password`


#### get Restaurant 100002
`curl -s http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### get All Restaurants
`curl -s http://localhost:8080/lunch-voting/rest/restaurants --user admin@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"Обновленный ресторан"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"Новый ресторан"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/restaurants --user admin@gmail.com:admin`

#### get Restaurant by Name
`curl -s http://localhost:8080/lunch-voting/rest/rest/restaurants/by?name=Megobari --user admin@gmail.com:admin`
