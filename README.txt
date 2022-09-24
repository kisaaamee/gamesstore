Hello,

GAME BOX web-aplication.

1) To run application need to have preinstalled postgres 14.5
DB: name gds
PORT: 5432
user role: mikita
user password: Amk2010
(I have added images into: scr/resources/manual/postgres folder)

2) Import project gamesstore
(imame with java project settings: scr/resources/manual/java)

3) After running application you could user is on localhost:8080

4) Before doing anything i suggest you to add demo game objects under button "use demo"

5) when you register first user it automaticaly has role -ADMIN, further registrations create only -USERS,
but you can change it in ADMIN PANEL (top right corner). this panel also prvides to user panel,
when an everage user is loged in.

6) in this aplication admin can -> change user roles, create, delete and edit GameBoxes -- game box is an entity like
product.

7) user can check game box information, and also check other users profiles, and also he can user his shopping cart

8) it was not so much time to build a microservice from every module - like payment, product, user and admin,
and also to build message broker. It is interesting to do it in future.

if you have any questions you can ask me directly: mikita.valadzko@gmail.com or by whatsapp or telegram: +48792272133

I hope you will like it. I tried to make it simple and in some places to use different stratedy like creatirng DTO objects
to show that i can create code in a differet way.
