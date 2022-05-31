package com.HWday19.Service;

import com.HWday19.domain.players;
import com.HWday19.domain.players;
import com.HWday19.repository.playerRepository;
import com.HWday19.repository.playerRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class playerServiceImpl implements playerService{
    private final playerRepository playerRepository1;

    @Autowired
    public playerServiceImpl(playerRepository playerRepository1){
        this.playerRepository1=playerRepository1;
    }

    @Override
    public players getPlayerById(long ID) {
        players res = playerRepository1.getByID(ID);
        if(res==null){
            throw new RuntimeException();
        }
        return res;
    }

    @Override
    public List<players> getAll() {
        return playerRepository1.getAll();
    }
}
