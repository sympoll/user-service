# User Service

## Table of Contents

- [1. About](#1-about)
- [2. Architecture](#2-architecture)
  - [2.1 Ports](#21-ports)
  - [2.2 User Service Schema](#22-user-service-schema)
  - [2.3 Endpoints](#23-endpoints)
    - [2.3.1 Login a User](#231-login-a-user)
    - [2.3.2 Update profile picture URL](#232-update-profile-picture-url)
    - [2.3.3 Update banner picture URL](#233-update-banner-picture-url)
    - [2.3.4 Update profile description](#234-gupdate-profile-description)
    - [2.3.5 Get all Users](#235-get-all-users)
    - [2.3.6 Get User by ID](#236-get-user-by-id)
    - [2.3.7 Get User by username](#237-get-user-by-username)
    - [2.3.8 Get Users by multiple IDs](#238-get-users-by-multiple-ids)
    - [2.3.9 Delete User by ID](#239-delete-user-by-id)
    - [2.3.10 Check if User ID exists](#2310-check-id-user-id-exists)
    - [2.3.11 Check if username exists](#2311-check-id-username-exists)
    - [2.3.12 Check if email exists](#2312-check-id-email-exists)

- [3. Error Codes](#3-error-codes)
- [4. Notes](#4-notes)

<br />

## 1. About

The User Service is a RESTful API that manages user data such as creation, retrieval, updates, and deletion of user profiles. It interacts with other services to handle user-related functionalities, such as authentication and authorization.

<br />

## 2. Architecture

### 2.1. Ports

- **Service port:** 8083
- **Database port:** 5433

<br />

### 2.2. User Service Schema

The database schema includes the following tables:

```sql
CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```


<br />

### 2.3) Endpoints

**BASE URL:** `/api/user`.

All calls to this service must start with the base URL. Any additional URL fields will be specified
if relevant.

---

#### 2.3.1) Login a User

- **Method:**  POST

- **Description:**  Creates a new User.

- **Request Body:**

```json
{
    "userId": "string",
    "username": "string",
    "email": "string"
}
```

- **Response:**

```json
{
    "userId": "uuid",
    "username": "string",
    "email": "string",
    "description": "string",
    "profilePictureUrl": "string",
    "profileBannerUrl": "string",
    "timeCreated": "2024-07-27T00:00:00Z"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `201 Created` – User created successfully.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.2) Update profile picture URL

- **Method:**  POST

- **Endpoint:**  `/profile-picture-url`

- **Description:**  Updates the User's profile picture URL.

- **Request Body:**

```json
{
    "userId": "string",
    "profilePictureUrl": "string"
}
```

- **Response:**

```json
{
    "userId": "uuid"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.3) Update banner picture URL

- **Method:**  POST

- **Endpoint:**  `/banner-picture-url`

- **Description:**  Updates the User's banner picture URL.

- **Request Body:**

```json
{
    "userId": "string",
    "bannerPictureUrl": "string"
}
```

- **Response:**

```json
{
    "userId": "uuid"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.4) Update profile description

- **Method:**  POST

- **Endpoint:**  `/description`

- **Description:**  Updates the User's profile description.

- **Request Body:**

```json
{
    "userId": "string",
    "description": "string"
}
```

- **Response:**

```json
{
    "userId": "uuid"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.5) Get all Users

- **Method:**  GET

- **Endpoint:**  `/all`

- **Description:**  Retrieves all Users from the DB.

- **Response:**

```json
[
  {
    "userId": "uuid",
    "username": "string",
    "email": "string",
    "description": "string",
    "profilePictureUrl": "string",
    "profileBannerUrl": "string",
    "timeCreated": "2024-07-27T00:00:00Z"
  }
]
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.6) Get User by ID

- **Method:**  GET

- **Endpoint:**  `/by-user-id`

- **Description:**  Retrieves a User by its ID.

- **Query Parameters:**
  - `userId` (UUID) – The ID of the user to retrieve.

- **Response:**

```json
{
  "userId": "uuid",
  "username": "string",
  "email": "string",
  "description": "string",
  "profilePictureUrl": "string",
  "profileBannerUrl": "string",
  "timeCreated": "2024-07-27T00:00:00Z"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.7) Get User by username

- **Method:**  GET

- **Endpoint:**  `/by-username`

- **Description:**  Retrieves a User by its username.

- **Query Parameters:**
  - `username` (string) – The username of the user to retrieve.

- **Response:**

```json
{
  "userId": "uuid",
  "username": "string",
  "email": "string",
  "description": "string",
  "profilePictureUrl": "string",
  "profileBannerUrl": "string",
  "timeCreated": "2024-07-27T00:00:00Z"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.8) Get Users by multiple IDs

- **Method:**  GET

- **Endpoint:**  `/list-by-user-ids`

- **Description:**  Retrieves a Users list by their IDs.

- **Query Parameters:**
  - `userIds` (List<UUID>) – The IDs of the users to retrieve.

- **Response:**

```json
[
  {
    "userId": "uuid",
    "username": "string",
    "email": "string",
    "description": "string",
    "profilePictureUrl": "string",
    "profileBannerUrl": "string",
    "timeCreated": "2024-07-27T00:00:00Z"
  }
]
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.9) Delete User by ID

- **Method:**  DELETE

- **Endpoint:**  `/by-user-id`

- **Description:**  Deletes a User by its ID.

- **Query Parameters:**
  - `userId` (UUID) – The ID of the user to delete.

- **Response:**

```json
{
  "userId": "uuid"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.10) Check if User ID exists

- **Method:**  GET

- **Endpoint:**  `/id`

- **Description:**  Retrieves true if the User ID exists. Otherwise retrieves false.

- **Query Parameters:**
  - `userId` (UUID) – The ID of the user to check.

- **Response:**

```json
{
  "isExists": "boolean"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.11) Check if username exists

- **Method:**  GET

- **Endpoint:**  `/username`

- **Description:**  Retrieves true if the username exists. Otherwise retrieves false.

- **Query Parameters:**
  - `username` (string) – The username of the user to check.

- **Response:**

```json
{
  "isUsernameExists": "boolean"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

---

#### 2.3.12) Check if User email exists

- **Method:**  GET

- **Endpoint:**  `/email`

- **Description:**  Retrieves true if the username exists. Otherwise retrieves false.

- **Query Parameters:**
  - `email` (string) – The email of the user to check.

- **Response:**

```json
{
  "isEmailExists": "boolean"
}
```

- **Response HTTP Status:**
  - `200 OK` – Successfully completed request.

  - `400 Bad Request` – Invalid input data.

<br />


## 3) Error Codes

- `400 Bad Request` – The request could not be understood or was missing required parameters.

- `404 Not Found` – The specified resource could not be found.

- `500 Internal Server Error` – An error occurred on the server.

<br />



## 4) Notes

- Make sure to include the `Content-Type: application/json` header in your requests.

