----
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and **README.md with API documentation and curl commands to get data for voting and vote.**

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

-----------------------------

## Curl samples (application deployed in application context lunch-voting).

### /rest/admin/users

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

### /rest/profile

#### get AuthorizedUser
`curl -s http://localhost:8080/lunch-voting/rest/profile --user admin@gmail.com:admin`

#### delete AuthorizedUser
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/profile --user user@yandex.ru:password`

#### register new User
`curl -s -X POST -d '{"name":"NewName", "email":"newmail@gmail.com", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/profile/register`

#### update AuthorizedUser
`curl -s -X PUT -d '{"name":"UpdatedName", "email":"updatedmail@gmail.com", "password":"updatedPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/profile --user user@yandex.ru:password`

### /rest/restaurants

#### get Restaurant by Id
`curl -s http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### get All Restaurants
`curl -s http://localhost:8080/lunch-voting/rest/restaurants --user admin@gmail.com:admin`

#### get Restaurant by Name
`curl -s http://localhost:8080/lunch-voting/rest/rest/restaurants/by?name=Megobari --user admin@gmail.com:admin`

#### vote for Restaurant
`curl -s -X POST http://localhost:8080/lunch-voting/rest/restaurants/100002/vote --user user@yandex.ru:password`

### /rest/admin/restaurants

#### create Restaurant
`curl -s -X POST -d '{"name":"Новый ресторан"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/restaurants --user admin@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"Обновленный ресторан"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/restaurants/100002 --user admin@gmail.com:admin`

### /rest/lunches

#### get Lunch Menu by Id
`curl -s http://localhost:8080/lunch-voting/rest/lunches/100009 --user user@yandex.ru:password`

#### get All Lunch Menus on today
`curl -s http://localhost:8080/lunch-voting/rest/lunches/today --user user@yandex.ru:password`

#### get All Lunch Menus by Date
`curl -s http://localhost:8080/lunch-voting/rest/lunches/by?date="2020-01-06" --user user@yandex.ru:password`

#### get Lunch Menu by RestaurantId on today
`curl -s http://localhost:8080/lunch-voting/rest/lunches/restaurants/100002/today" --user user@yandex.ru:password`

#### get Lunch Menu by RestaurantId and date
`curl -s http://localhost:8080/lunch-voting/rest/lunches/restaurants/100002/by?date="2020-01-06"" --user user@yandex.ru:password`

### /rest/admin/lunches

#### delete Lunch Menu
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/admin/lunches/100009 --user admin@gmail.com:admin`

### /rest/admin/dishes

#### get Dish by Id
`curl -s http://localhost:8080/lunch-voting/rest/admin/dishes/100017 --user user@yandex.ru:password`

#### add Dish for Restaurant 100002
`curl -s -X POST -d '{"name":"Новое блюдо", "price":"100"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/admin/dishes/restaurants/100002 --user admin@gmail.com:admin`

#### update Dish
`curl -s -X PUT -d '{"name":"Обновленное блюдо", "price":"200"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunch-voting/rest/admin/dishes/100017 --user admin@gmail.com:admin`

#### delete Dish
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/admin/dishes/100017 --user admin@gmail.com:admin`

### /rest/admin/votes

#### get Vote by Id
`curl -s http://localhost:8080/lunch-voting/rest/admin/votes/100040 --user admin@gmail.com:admin`

#### get Votes on today
`curl -s http://localhost:8080/lunch-voting/rest/admin/votes/today --user admin@gmail.com:admin`

#### get Votes by date
`curl -s http://localhost:8080/lunch-voting/rest/admin/votes/by?date="2020-01-06" --user admin@gmail.com:admin`

#### delete Vote
`curl -s -X DELETE http://localhost:8080/lunch-voting/rest/admin/votes/100040 --user admin@gmail.com:admin`