package com.example.magasin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.magasin.modele.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findUserByLoginAndMdp( String login, String mdp );

}
