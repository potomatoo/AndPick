# :rocket: &PICK
## 지금 당장  &PICK 하라

&PICK은 다양한 분야의 뉴스기사 및 기술블로그의 정보를 제공합니다.  

구독 : 관심있는 뉴스나 기술 블로그를 피드에 추가하여 최신 정보를 확인

보드 : 구독하고 있는 채널의 기사를 저장하여 보관

마이페이지 : 다양한 편집 기능을 통해 자신의 생각을 정리하여 

&PICK은 당신의 밝은 미래를 응원합니다.


# :tada: Getting Started

## Front end
```
cd ./frontend
npm install
npm run serve or npm run build
```
## Back end
```
cd ./backend
mvn package
java -jar target/Teletubbies-0.0.1-SNAPSHOT.jar
```
## ChormeExtenstion
### [크롬 익스텐션 다운로드](https://chrome.google.com/webstore/detail/pickclipper/bggenjcdpkngebimckblkeeiciegaenk?utm_source=chrome-ntp-icon)

# :wrench: Configuration

## require
- [Mavne](https://maven.apache.org/)
- [Node.js](https://nodejs.org/en/)
- [Mysql](https://www.mysql.com/)
- [Mongdb](https://www.mongodb.com/)
- [Redis](https://redis.io/)
- [Google login](https://developers.google.com/identity/sign-in/web)
- [JWT](https://jwt.io/)

## Frontend Config
### BaseUrl Config ( ./frontend/src/service/axios.service.ts )
```
...
export class Axios {
  static readonly instance: AxiosInstance = axios.create({
    baseURL: `Your_base_url`,
    timeout: 10000
  });
}
...
```

### Google Login Config ( ./frontend/src/main.ts )
```
...
Vue.use(GAuth, {
  clientId: `Google_Client_ID`,
  scope: "profile email https://www.googleapis.com/auth/plus.login",
});
...
```
## Backend Config

### DB Config ( ./backend/src/main/resources/application.properties )
```
# MySql
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=  `mysql_url`
spring.datasource.username= `mysql_user_name`
spring.datasource.password= `mysql_user_password`

#Redis
spring.cache.type=redis
spring.redis.host= `redis_url`
spring.redis.port= `redis_port`

#MongoDB
spring.data.mongodb.database= `mongodb_database_name`
spring.data.mongodb.port=  `mongodb_port`

```
### Google Login Config( ./backend/src/main/java/com/ssafy/config/GoogleLoginConfig.java )
```
	public static final String GOOGLE_CLIENT_ID = `Google_Client_ID`;
	public static final String GOOGLE_SECRIT_ID = `Google_Secrit_ID`;
    ...
    	@Bean
	OAuth2Parameters oAuth2Parameters() {
		OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
		oAuth2Parameters.setRedirectUri( `Redirect_url` );
		oAuth2Parameters.setScope( `Google_Login_scope` );
		return oAuth2Parameters;
	}

```
### JWT Config ( ./backend/src/main/java/com/ssafy/config/JwtProperties.java )
```
public class JwtProperties {
    ...
    public static final String SECRET = `Your_JWT_Secrit_Key`;
    ...
}
```

# :pencil2: Tech Stack
## Front end
- [Vue](https://vuejs.org/)
- [Vuex](https://vuex.vuejs.org/kr/)
- [Vue-Router](https://router.vuejs.org/kr/)
- [Eslint](https://eslint.org/)
- [TypeScript](https://www.typescriptlang.org/)
- [Vuelidate](https://vuelidate.js.org/)

## Back end
- [Spring boot](https://spring.io/projects/spring-boot)
- [Spring boot Security](https://spring.io/projects/spring-security-oauth)
- [JWT](https://jwt.io/)

## DB
- [Mysql](https://www.mysql.com/)
- [Mongdb](https://www.mongodb.com/)
- [Redis](https://redis.io/)

## CI/CD
- [Jenkins](https://www.jenkins.io/)



# :busts_in_silhouette: Author

## Team 텔레토비
이연재 : 막내 팀장

김동욱 : 진공청소기

윤동현 : 슈퍼개~~발자~~

김준호 : 멀티 플레이어