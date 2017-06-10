**getRestaurantList**
----
  전체 식당의 목록을 가져온다.

* **URL**

  /restaurant

* **Method:**
  
  `GET`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `[
                      {
                        "id": 1,
                        "name": "돈내코 순두부",
                        "open": false,
                        "image": null,
                        "startTime": 9000000,
                        "endTime": 16200000
                      },
                      {
                        "id": 2,
                        "name": "레스토랑스",
                        "open": false,
                        "image": "/facepalm.jpg",
                        "startTime": 0,
                        "endTime": 32400000
                      }
                ]`

**getRestaurantById**
----
  해당 ID를 가진 식당의 정보를 가져온다.

* **URL**

  /restaurant/:id

* **Method:**
  
  `GET`

*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                      "id": 1,
                      "name": "돈내코 순두부",
                      "open": false,
                      "image": null,
                      "startTime": 9000000,
                      "endTime": 16200000
                  }`
  
**addRestaurant**
----
  해당 정보를 가진 식당을 추가한다. 

* **URL**

  /restaurant/

* **Method:**
  
  `POST`

* **Data Params**

  <_이미지가 필드에 존재하기 때문에 반드시 form-data로 보낼것_>
  
  **Required:**
  
  `name=[string]`
  
  `image-file=[file]`
  
  `startTime=[time]`
  
  `endTime=[time]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 4,
                    "name": "testRestaurant1",
                    "open": null,
                    "image": "/facepalm.jpg",
                    "startTime": 0,
                    "endTime": 14400000
                  }`
                  
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`

  <_특정 필드가 없거나 이름이 중복되었을때_>

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{
                    "timestamp": 1497081699902,
                    "status": 400,
                    "error": "Bad Request",
                    "exception": "kr.ac.jejunu.exceptions.ObjectDuplicatedException",
                    "message": "object duplicated",
                    "path": "/restaurant"
                  }`
                  
**updateRestaurant**
----
  해당 ID를 가진 식당의 정보를 수정한다.

* **URL**

  /restaurant/:id

* **Method:**
  
  `PUT`

*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**
  **Required:**
  `
  {
  	"name":[string],
  	"startTime": [string],
  	"endTime": [string]"
  }
  `  
  
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 4,
                    "name": "testRestaurant2",
                    "open": null,
                    "image": null,
                    "startTime": 3600000,
                    "endTime": 18000000
                  }`
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`

  <_특정 필드가 없거나 이름이 중복되었을때_>

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{
                    "timestamp": 1497081699902,
                    "status": 400,
                    "error": "Bad Request",
                    "exception": "kr.ac.jejunu.exceptions.ObjectDuplicatedException",
                    "message": "object duplicated",
                    "path": "/restaurant"
                  }`

  <_대상 식당이 존재하지 않을때_>

  * **Code:** 404 NOT FOUND<br />
    **Content:** `{
                    "timestamp": 1497083324731,
                    "status": 404,
                    "error": "Not Found",
                    "exception": "kr.ac.jejunu.exceptions.RestaurantNotExistException",
                    "message": "restaurant not exist",
                    "path": "/restaurant/99"
                  }`
                                    
**deleteRestaurantById**
----
  해당 ID를 가진 식당을 삭제한다 

* **URL**

  /restaurant/:id

* **Method:**
  
  `DELETE`

*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
                  }`

* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`
                  
**requestUpdate**
----
  식당 영업정보 변경(Toggle)을 요청한다 

* **URL**

  /update-request/

* **Method:**
  
  `POST`

* **Data Params**
  **Required:**
  
  `
  {
    restaurant-id:[integer]
  }
  `


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
                  }`
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`

  <_대상 식당이 존재하지 않을때_>

  * **Code:** 404 NOT FOUND<br />
    **Content:** `{
                    "timestamp": 1497083324731,
                    "status": 404,
                    "error": "Not Found",
                    "exception": "kr.ac.jejunu.exceptions.RestaurantNotExistException",
                    "message": "restaurant not exist",
                    "path": "/restaurant/99"
                  }`
                                    
**getCommentOfRestaurant**
----
  해당 식당의 댓글목록을 가져온다.

* **URL**

  /comment/:id

* **Method:**
  
  `GET`
*  **URL Params**

   **Required:**
 
   `id=[integer]`
   
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `[
                  ]`
  
**addComment**
----
  해당 식당에 댓글을 추가한다 

* **URL**

  /comment/

* **Method:**
  
  `POST`

* **Data Params**
  **Required:**
  
  `text=[string]`
  
  `restaurant-id=[integer]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
                  }`
                  
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }
                  
  <_대상 식당이 존재하지 않을때_>

  * **Code:** 404 NOT FOUND<br />
    **Content:** `{
                    "timestamp": 1497083324731,
                    "status": 404,
                    "error": "Not Found",
                    "exception": "kr.ac.jejunu.exceptions.RestaurantNotExistException",
                    "message": "restaurant not exist",
                    "path": "/restaurant/99"
                  }`


**deleteCommentById**
----
  해당 ID를 가진 댓글을 삭제한다 

* **URL**

  /comment/:id

* **Method:**
  
  `DELETE`

*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
                  }`

* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`
                  
**getUserById**
----
  사용자 정보를 받아온다

* **URL**

  /user/:id

* **Method:**
  
  `GET`
*  **URL Params**

   **Required:**
 
   `id=[integer]`
   
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 1,
                    "name": "test",
                    "password": "1234"
                  }`
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`

**addUser**
----
  사용자를 추가한다(회원가입) 

* **URL**

  /user/

* **Method:**
  
  `POST`

* **Data Params**
  **Required:**
  
  `name=[string]`
  
  `password=[string]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 1,
                    "name": "test",
                    "password": "1234"
                  }`
                  
* **Error Response:**

  <_특정 필드가 없거나 이름이 중복되었을때_>

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{
                    "timestamp": 1497081699902,
                    "status": 400,
                    "error": "Bad Request",
                    "exception": "kr.ac.jejunu.exceptions.ObjectDuplicatedException",
                    "message": "object duplicated",
                    "path": "/restaurant"
                  }`
 
**updateUser**
----
  사용자 정보를 수정한다 

* **URL**

  /user

* **Method:**
  
  `PUT`
  
* **Data Params**
  **Required:**
  
  `{
   	"password":[string]
   }`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 4,
                    "name": "test11",
                    "password": "1234"
                  }`
                  
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`
**login**
----
  ID/PW로 로그인한다 

* **URL**

  /user/login

* **Method:**
  
  `POST`

*  **URL Params**

   **Required:**
 
   `username=[string]`
   
   `password=[string]`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "id": 4,
                    "name": "test11",
                    "password": "1234"
                  }`
                  
* **Error Response:**

  <_인증되지 않았을때_>

  * **Code:** 401 UNAUTHORIZED
    **Content:** `{
                    "timestamp": 1497081723425,
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/restaurant"
                  }`