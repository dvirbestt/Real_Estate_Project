package com.example.RealEstateSite.repository;

import com.example.RealEstateSite.model.Post;
import com.example.RealEstateSite.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findByCity(String city);

    @Query(value = "SELECT * FROM post WHERE city=?1 AND num_of_rooms=?2", nativeQuery = true)
    List<Post> findByCityAndNumOfRooms(String city,float numOfRooms);

    @Query(value = "SELECT * FROM post ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Post> getRand();

    @Query(value = "SELECT DISTINCT city FROM post",nativeQuery = true)
    List<String> getNames();

    @Query(value = "SELECT * FROM post WHERE city=?1 AND num_of_rooms=?2 AND price <=?3", nativeQuery = true)
    List<Post> getByCityRoomsAndPrice(String city,float numOfRooms,float price);
    @Query(value = "SELECT * FROM post WHERE city=?1 AND num_of_rooms=?2 AND price <=?3 AND property_type=?4", nativeQuery = true)
    List<Post> getByAll(String city, float numOfRooms, float price, int propertyType);
    @Query(value = "SELECT * FROM post WHERE city=?1 AND num_of_rooms=?2 AND property_type=?3",nativeQuery = true)
    List<Post> getByCityRoomsAndType(String city,float numOfRooms,int propertyType);

    @Query(value = "SELECT * FROM post WHERE city=?1 AND property_type=?2",nativeQuery = true)
    List<Post> getByCityAndType(String city,int propertyType);

    @Query(value = "SELECT * FROM post WHERE city=?1 AND price<=?2 AND property_type=?3",nativeQuery = true)
    List<Post> getByCityPriceAndType(String city, float price, int propertyType);

    @Query(value = "SELECT * FROM post WHERE author=?1", nativeQuery = true)
    List<Post> showMyPosts(String author);

    @Query(value = "SELECT * FROM post WHERE city=?1 AND price<=?2", nativeQuery = true)
    List<Post> getByCityAndPrice(String city,float price);


}
