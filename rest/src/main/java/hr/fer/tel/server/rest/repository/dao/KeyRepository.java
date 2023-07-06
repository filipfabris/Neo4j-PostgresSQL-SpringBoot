package hr.fer.tel.server.rest.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.tel.server.rest.model.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {
}
