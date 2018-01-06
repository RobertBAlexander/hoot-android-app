package app.hoot.main;

/**
 * Created by Robert Alexander on 29/12/2017.
 */

import java.util.List;

import app.hoot.model.Hoot;
import app.hoot.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HootService {
    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @GET("/api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("/api/users/authenticate")
    Call<User> authenticate(@Body User user);

    @POST("/api/users")
    Call<User> createUser(@Body User User);

    @PUT("/api/users")
    Call<User> updateUser(@Body User user);

    @GET("/api/hoots")
    Call<List<Hoot>> getAllHoots();

    @GET("/api/users/{id}/followed")
    Call<List<Hoot>> getFollowedHoots(@Path("id") String id);

/*    @GET("/api/candidates")
    Call<List<Candidate>> getAllCandidates();*/

//possible version for storing hoot with user
/*    @POST("/api/users/{id}/hoots")
    Call<Hoot> createHoot(@Path("id") String id, @Body Hoot hoot);*/

    @POST("/api/hoots")
    Call<Hoot> createHoot(@Body Hoot hoot);

    @POST("/api/followuser/{id}")
    Call<User> followUser(@Path("id") String id);

    @POST("/api/unfollow/{id}")
    Call<User> unfollowUser(@Path("id") String id);

    @POST("/api/users/{id}/follow")
    Call<User> follow(@Path("id") String _id, @Body String user);

    @POST("/api/users/{id}/unfollow")
    Call<User> unfollow(@Path("id") String _id, @Body String user);


}
