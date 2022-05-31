package com.HWday19.repository;

import com.HWday19.domain.players;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface playerRepository {
    players getByID(long ID);
    List<players> getAll();
}
