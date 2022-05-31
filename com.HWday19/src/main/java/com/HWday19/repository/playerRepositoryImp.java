package com.HWday19.repository;

import com.HWday19.domain.players;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class playerRepositoryImp implements playerRepository{
    @Override
    public players getByID(long ID) {
        return null;
    }

    @Override
    public List<players> getAll() {
        return null;
    }
}
