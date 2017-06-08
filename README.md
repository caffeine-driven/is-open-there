**getRestaurantList**
----
  전체 식당의 목록을 가져온다.

* **URL**

  /restaurant

* **Method:**
  
  `GET`

* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "restaurants": [
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
                    ]
                  }`

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
                    "restaurant": {
                      "id": 1,
                      "name": "돈내코 순두부",
                      "open": false,
                      "image": null,
                      "startTime": 9000000,
                      "endTime": 16200000
                    }
                  }`
  
**addRestaurant**
----
  해당 정보를 가진 식당을 추가한다. 

* **URL**

  /restaurant/

* **Method:**
  
  `POST`

* **Data Params**
  **Required:**
  
  `name=[string]`
  
  `image=[file]`
  
  `startTime=[time]`
  
  `endTime=[time]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "restaurant": {
                      "id": 1,
                      "name": "돈내코 순두부",
                      "open": false,
                      "image": null,
                      "startTime": 9000000,
                      "endTime": 16200000
                    }
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
    
  <_Content-type must be `application/x-www-form-urlencoded` _>
  
  **Required:**
  
  `name=[string]`
  
  `startTime=[time]`
  
  `endTime=[time]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "restaurant": {
                      "id": 1,
                      "name": "돈내코 순두부",
                      "open": false,
                      "image": null,
                      "startTime": 9000000,
                      "endTime": 16200000
                    }
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
                    "deleted": true
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
  
  `restaurant-id=[integer]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
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
    **Content:** `{
                    "restaurant_id": 1,
                    "comments": []
                  }`
  
**addComment**
----
  해당 식당에 댓글을 추가한다 

* **URL**

  /comment/:id

* **Method:**
  
  `POST`

*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**
  **Required:**
  
  `text=[string]`
  
  `image=[file]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "result": true
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
                    "deleted": true
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
                    "user": {
                        "id": 4,
                        "name": "test11",
                        "password": "1234"
                      }
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
  
  `username=[string]`
  
  `password=[string]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "user": {
                        "id": 4,
                        "name": "test11",
                        "password": "1234"
                      }
                  }`
                  
**updateUser**
----
  사용자를 추가한다(회원가입) 

* **URL**

  /user/:id

* **Method:**
  
  `PUT`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`
   
* **Data Params**
  **Required:**
  
  `password=[string]`


* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{
                    "user": {
                        "id": 4,
                        "name": "test11",
                        "password": "1234"
                      }
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
                    "user": {
                        "id": 4,
                        "name": "test11",
                        "password": "1234"
                      }
                  }`