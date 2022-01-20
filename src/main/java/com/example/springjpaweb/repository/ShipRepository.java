package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Ship;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {


    @Query("SELECT s FROM Ship s WHERE s.company = 'Linda'")
    List<Ship> findAllLindaShipsFromCompany(Sort sort); // pri volani pak ...(Sort.by("company")
    //List<Ship> findAllShipsFromCompany();

    @Query("SELECT s FROM Ship s WHERE s.company = :company and s.name = :name")
    List<Ship> findShipsByCompanyAndName(
            @Param("company") String company, //company se nemusi matchovat mezi sebou
            @Param("name") String name);

        //works
//    @Query("SELECT s FROM Ship s WHERE s.company = :company and s.name = :name")
//    Ship findShipByCompanyAndName(
//            @Param("company") String company, //company se nemusi matchovat mezi sebou
//            @Param("name") String name);



//    @Modifying
//    @Query("update User u set u.status = :status where u.name = :name")
//    int updateUserSetStatusForName(@Param("status") Integer status,
//                                   @Param("name") String name);


//    @Modifying
//    @Query(
//            value =
//                    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//            nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//                    @Param("status") Integer status, @Param("email") String email);


//    @Query(
//            value = "SELECT * FROM USERS u WHERE u.status = 1",
//            nativeQuery = true)
//    Collection<User> findAllActiveUsersNative();
}

