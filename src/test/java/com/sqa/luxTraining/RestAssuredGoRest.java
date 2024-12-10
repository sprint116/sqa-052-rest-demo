package com.sqa.luxTraining;

import com.sqa.model.gorest.Post;
import com.sqa.model.gorest.User;
import com.sqa.utils.TestLogger;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class RestAssuredGoRest implements TestLogger {
    static final String BASE_URL = "https://gorest.co.in";
    String token = "Bearer eaeebb68b29e14ba28ac86fdbc4e6914d8492375ab2d5c41447627fd16af8669";
    String randomNum = String.valueOf(new Random().nextInt(500) + 1);
    String randomEmail = String.format("%s.ramakrishna@11ce.com", new Random().nextInt(500) + 1);

    /*todo User data*/

    User getCommonUserToGet() {
        log("Start: getCommonUserToGet");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get("/public/v2/users");
        log(response.prettyPrint());
        log("End: getCommonUserToGet");

        return new User(
                response.jsonPath().get("[0].id"),
                response.jsonPath().get("[0].name"),
                response.jsonPath().get("[0].email"),
                response.jsonPath().get("[0].gender"),
                response.jsonPath().get("[0].status")
        );
    }

    /*todo Post data*/

    Post getCommonPostToGet() {
        log("Start: getCommonPostToGet");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/users/%s/posts", getCommonUserToGet().getId()));
        log(response.prettyPrint());
        log("End: getCommonPostToGet");

        return new Post(
                response.jsonPath().get("[0].id"),
                response.jsonPath().get("[0].user_id"),
                response.jsonPath().get("[0].title"),
                response.jsonPath().get("[0].body")
        );
    }

    /*todo get List Users*/

    @Test
    void getListUsers() {
        log("Start: getListUsers");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get("/public/v2/users");
        log(response.prettyPrint());
        log("End: getListUsers");

        List<User> users = Arrays.asList(response.as(User[].class));

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertFalse(users.isEmpty())
        );

    }

    /*todo Create User*/

    @Test
    void postUserIncorrectEmail() {
        log("Start: postUserIncorrectEmail");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body(getCommonUserToGet().withEmail("128.ramakrishna@11ce.com"))
                .when()
                .post("/public/v2/users");
        log(response.prettyPrint());
        log("End: postUserIncorrectEmail");

        assertAll(
                () -> assertEquals(422, response.statusCode()),
                () -> assertEquals("has already been taken", response.jsonPath().get("[0].message")),
                () -> assertEquals("email", response.jsonPath().get("[0].field"))
        );
    }

    @Test
    void postUser() {
        User postedUser = getCommonUserToGet()
                .withId(null)
                .withEmail(randomEmail);
        User returnUser = postUser(postedUser);

        assertAll(
                () -> assertNotNull(returnUser.getId()),
                () -> assertEquals(postedUser.getName(), returnUser.getName()),
                () -> assertEquals(postedUser.getEmail(), returnUser.getEmail()),
                () -> assertEquals(postedUser.getGender(), returnUser.getGender()),
                () -> assertEquals(postedUser.getStatus(), returnUser.getStatus())
        );
    }

    private User postUser(User postedUser) {
        log("Start: postUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body(postedUser)
                .when()
                .post("/public/v2/users");
        log(response.prettyPrint());
        log("End: postUser");

        assertEquals(201, response.statusCode());
        return response.body().as(User.class);
    }

    /*todo to get User*/

    @Test
    void getIncorrectUser() {
        log("Start: getIncorrectUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/users/%s", getCommonUserToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: getIncorrectUser");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void getUser() {
        User awaitUser = getCommonUserToGet();
        User returnUser = getUser(awaitUser.getId());

        assertAll(
                () -> assertEquals(awaitUser, returnUser));
    }

    private User getUser(int userId) {
        log("Start: getUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/users/%d", userId));
        log(response.prettyPrint());
        log("End: getUser");

        assertEquals(200, response.statusCode());
        return response.body().as(User.class);
    }

    /*todo to Update User*/

    @Test
    void patchIncorrectUser() {
        log("Start: patchIncorrectUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(getCommonUserToGet())
                .when()
                .patch(String.format("/public/v2/users/%d", getCommonUserToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: patchIncorrectUser");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void patchUser() {
        User awaitUser = getCommonUserToGet().withGender("female").withName("Testing Test2");
        User returnUser = patchUser(awaitUser.getId(), (awaitUser));

        assertAll(
                () -> assertEquals(awaitUser, returnUser));
    }

    private User patchUser(int userId, User patchUser) {
        log("Start: patchUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(patchUser)
                .when()
                .patch(String.format("/public/v2/users/%d", userId));
        log(response.prettyPrint());
        log("End: patchUser");

        assertEquals(200, response.statusCode());

        return response.body().as(User.class);
    }

    @Test
    void putIncorrectUser() {
        log("Start: putIncorrectUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(getCommonUserToGet())
                .when()
                .put(String.format("/public/v2/users/%d", getCommonUserToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: putIncorrectUser");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void putUser() {
        User awaitUser = getCommonUserToGet().withGender("female").withName("Testing Test2");
        User returnUser = putUser(awaitUser.getId(), (awaitUser));

        assertAll(
                () -> assertEquals(awaitUser, returnUser));
    }

    private User putUser(int userId, User putUser) {
        log("Start: putUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(putUser)
                .when()
                .put(String.format("/public/v2/users/%d", userId));
        log(response.prettyPrint());
        log("End: putUser");

        assertEquals(200, response.statusCode());

        return response.body().as(User.class);
    }

    /*todo to Delete User*/

    @Test
    void delIncorrectUser() {
        log("Start: getUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(String.format("/public/v2/users/%d", 0));
        log(response.prettyPrint());
        log("End: getUser");

        assertEquals(404, response.statusCode());
    }

    @Test
    void delUser() {
        delUser(getCommonUserToGet().getId());
    }

    ;

    void delUser(int userId) {
        log("Start: getUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(String.format("/public/v2/users/%d", userId));
        log(response.prettyPrint());
        log("End: getUser");

        assertEquals(204, response.statusCode());
    }

    /*todo get List Post for User*/

    @Test
    void getListPostsForUser() {
        getListPostsForUser(getCommonUserToGet().getId());
    }

    List<Post> getListPostsForUser(int userId) {
        log("Start: getListPostsForUsers");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/users/%s/posts", userId));
        log(response.prettyPrint());
        log("End: getListPostsForUsers");

        List<Post> posts = Arrays.asList(response.as(Post[].class));

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertFalse(posts.isEmpty())
        );

        return posts;
    }

    /*todo to Create Post*/

    @Test
    void postIncorrectPost() {
        log("Start: postIncorrectPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body("{}")
                .when()
                .post(String.format("/public/v2/users/%s/posts", getCommonUserToGet().getId()));
        log(response.prettyPrint());
        log("End: postIncorrectPost");

        assertAll(
                () -> assertEquals(422, response.statusCode()),
                () -> assertEquals("can't be blank", response.jsonPath().get("[0].message")),
                () -> assertEquals("title", response.jsonPath().get("[0].field")),
                () -> assertEquals("can't be blank", response.jsonPath().get("[1].message")),
                () -> assertEquals("body", response.jsonPath().get("[1].field"))
        );
    }

    @Test
    void postPost() {
        Post postedPost = getCommonPostToGet()
                .withId(null)
                .withTitle(String.format("Test title %s", randomNum))
                .withBody(String.format("Test body %s", randomNum))
                .withUserId(getCommonUserToGet().getId());

        Post returnUser = postPost(postedPost);

        Post finalPostedPost = postedPost.withId(returnUser.getId());

        assertAll(
                () -> assertEquals(finalPostedPost, returnUser)
        );
    }

    private Post postPost(Post postedPost) {
        log("Start: postPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body(postedPost)
                .when()
                .post(String.format("/public/v2/users/%s/posts", getCommonUserToGet().getId()));
        log(response.prettyPrint());
        log("End: postPost");

        assertEquals(201, response.statusCode());
        return response.body().as(Post.class);
    }

    /*todo Get Post*/

    @Test
    void getIncorrectPost() {
        log("Start: getIncorrectPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/posts/%s", getCommonPostToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: getIncorrectPost");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void getPost() {
        Post awaitPost = getCommonPostToGet();
        Post returnPost = getPost(awaitPost.getId());

        assertAll(
                () -> assertEquals(awaitPost, returnPost));
    }

    private Post getPost(int PostId) {
        log("Start: getPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get(String.format("/public/v2/posts/%d", PostId));
        log(response.prettyPrint());
        log("End: getPost");

        assertEquals(200, response.statusCode());
        return response.body().as(Post.class);
    }

    /*todo to Update Post for User*/

    @Test
    void patchIncorrectPost() {
        log("Start: patchIncorrectPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(getCommonPostToGet())
                .when()
                .patch(String.format("/public/v2/posts/%d", getCommonPostToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: patchIncorrectPost");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void patchPost() {
        Post awaitPost = getCommonPostToGet().withTitle("Body 1").withTitle("Title 1");
        Post returnPost = patchPost(awaitPost.getId(), (awaitPost));

        assertAll(
                () -> assertEquals(awaitPost, returnPost));
    }

    private Post patchPost(int postId, Post patchPost) {
        log("Start: patchUser");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(patchPost)
                .when()
                .patch(String.format("/public/v2/posts/%d", postId));
        log(response.prettyPrint());
        log("End: patchUser");

        assertEquals(200, response.statusCode());

        return response.body().as(Post.class);
    }

    @Test
    void putIncorrectPost() {
        log("Start: putIncorrectPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(getCommonPostToGet())
                .when()
                .put(String.format("/public/v2/posts/%d", getCommonPostToGet().withId(0).getId()));
        log(response.prettyPrint());
        log("End: putIncorrectPost");

        assertAll(
                () -> assertEquals(404, response.statusCode()),
                () -> assertEquals("Resource not found", response.jsonPath().get("message"))
        );
    }

    @Test
    void putPost() {
        Post awaitPost = getCommonPostToGet().withTitle("Body 1").withTitle("Title 1");
        Post returnPost = putPost(awaitPost.getId(), (awaitPost));

        assertAll(
                () -> assertEquals(awaitPost, returnPost));
    }

    private Post putPost(int PostId, Post putPost) {
        log("Start: putPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(putPost)
                .when()
                .put(String.format("/public/v2/posts/%d", PostId));
        log(response.prettyPrint());
        log("End: putPost");

        assertEquals(200, response.statusCode());

        return response.body().as(Post.class);
    }

    /*todo to Delete Post for User*/

    @Test
    void delIncorrectPost() {
        log("Start: getPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(String.format("/public/v2/posts/%d", 0));
        log(response.prettyPrint());
        log("End: getPost");

        assertEquals(404, response.statusCode());
    }

    @Test
    void delPost() {
        delPost(getCommonPostToGet().getId());
    }

    void delPost(int postId) {
        log("Start: getPost");
        Response response = given()
                .baseUri(BASE_URL)
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(String.format("/public/v2/posts/%d", postId));
        log(response.prettyPrint());
        log("End: getPost");

        assertEquals(204, response.statusCode());
    }

    /*todo E2E Tests*/

    /*scenario 1*/
    @Test
    void newUserCreateNewPost() {
        User createUser = postUser(new User()
                .withId(null)
                .withEmail(randomEmail)
                .withStatus("active")
                .withGender("male")
        );

        Post createPost = postPost(getCommonPostToGet()
                .withId(null)
                .withTitle(String.format("Test title %s", randomNum))
                .withBody(String.format("Test body %s", randomNum))
                .withUserId(createUser.getId())
        );

        Post getCreatedPost = getPost(createPost.getId());

        assertAll(
                () -> assertEquals(createUser.getId(), createPost.getUserId()),
                () -> assertEquals(createPost, getCreatedPost)
        );
    }

    /*scenario 2*/
    @Test
    void userGetAllPostsAndCloneLastPost() {
        User getUser = getUser(getCommonUserToGet().getId());
        postPost(getCommonPostToGet()
                .withId(null)
                .withTitle(String.format("Test title %s", randomNum))
                .withBody(String.format("Test body %s", randomNum))
                .withUserId(getUser.getId())
        );
        List<Post> userPosts = getListPostsForUser(getUser.getId());
        Post lastPost = userPosts.get(userPosts.size() - 1);
        Post createClonePost = postPost(new Post()
                .withUserId(getUser.getId())
                .withId(null)
                .withBody(lastPost.getBody())
                .withTitle(lastPost.getTitle())
        );
        Post finalLastPost = lastPost.withId(null);
        Post finalCloneCreatePost = createClonePost.withId(null);

        assertAll(
                () -> assertEquals(finalLastPost, finalCloneCreatePost)
        );
    }

    /*scenario 3*/
    @Test
    void updateUserAndCheckPosts() {
        int i = 3;
        User getUser = getUser(getCommonUserToGet().getId());
        while (i >= 0) {
            postPost(getCommonPostToGet()
                    .withId(null)
                    .withTitle(String.format("Test title %s", randomNum))
                    .withBody(String.format("Test body %s", randomNum))
                    .withUserId(getUser.getId())
            );
            i--;
        }
        List<Post> userPostsBeforeUpdate = getListPostsForUser(getUser.getId());
        putUser(getUser.getId(),getUser.withGender("female").withName("Testing updateUserAndCheckPosts"));
        List<Post> userPostsAfterUpdate = getListPostsForUser(getUser.getId());

        assertAll(
                () -> assertEquals(userPostsBeforeUpdate, userPostsAfterUpdate)
        );
    }

    /*scenario 4*/
    @Test
    void returnUserDataScenario3AndDeleteUserScenario1(){
        /*I'm don't know*/
    }
}
