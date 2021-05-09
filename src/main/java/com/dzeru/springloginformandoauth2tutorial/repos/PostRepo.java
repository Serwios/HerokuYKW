package com.dzeru.springloginformandoauth2tutorial.repos;

import com.dzeru.springloginformandoauth2tutorial.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
}
