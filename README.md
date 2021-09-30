<h1 align="center">Welcome to springboot security and JWT 👋</h1>
<p>
  <a href="https://www.npmjs.com/package/springboot security" target="_blank">
    <img alt="Version" src="https://img.shields.io/npm/v/springboot security.svg">
  </a>
  <a href="https://github.com/kyubokWi/spring_security2" target="_blank">
    <img alt="License: orangeblue" src="https://img.shields.io/badge/License-orangeblue-yellow.svg" />
  </a>
</p>


> 스프링부트 Security와 JWT 공부


<br>

## Todo
- description

<br>

## Author

👤 **WIKYUBOK**

* Website: https://github.com/kyubokWi/
* Github: [@kyubokwi](https://github.com/kyubokwi)
* Reference: [@Amigoscode](https://www.youtube.com/channel/UC2KfmYEM4KCuA1ZurravgYw) spring security full version lecture

<br>
<br>

## Setting and dependencies 

<br>


### Setting

- vscode latest
- maven
- java 11 -v
- springboot 2.5.5 -v

<br>

### dependencies

- spring security
- thymeleaf
- spring web
- spring devtools
- lombok
- google guava 28.1-jre -v
- spring boot configuration processor
- jwt 0.11.2 -v
  - jjwt-api
  - jjwt-impl
  - jjwt-jackson


<br>
<br>


## JWT (Json Web Token)

<br>

##### Pros
- faster
- stateless
- used across many services (android, ios, web, 3rd party... )

##### Cons
- Compromised secret key
- no visibility to logged in users
- token can be stolen

##### Works
- client -> server ( sends credentials )
- server validates credential and creates and signs token
- server -> client ( sends token)
- client -> server ( every request with given token )
  - any request send to server included token
  - this time, filter as JwtUsernameAndPasswordAuthenticationFilter works 
- server ( validates given token ) 
  - JwtTokenVelifier filter works

<br>

##### Factor transformed by specific algorithm such as HS256
- encoded header ( ALGORITHM & TOKEN TYPE )
- encoded payload ( DATA )
- encoded my secret signature ( VERIFY SIGNATURE )




## 📝 License

Copyright © 2021 [@Orangeblue](https://github.com/kyubokwi).<br />


***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_