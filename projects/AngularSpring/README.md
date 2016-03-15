Based on code from this tutorial:

http://websystique.com/springmvc/spring-mvc-4-angularjs-example/

There are two ways of encoding an HTTP request. The traditional way uses URL encoding:
```
POST /blog/posts
Content-Type: application/x-www-form-urlencoded
Content-Length: 57

title=Hello%20World!&body=This%20is%20my%20first%20post!
```
Most front-end frameworks, like Angular, encode it with JSON:
```
POST /blog/posts
Content-Type: application/json
Content-Length: 57

{"title":"Hello World!","body":"This is my first post!”}
```
