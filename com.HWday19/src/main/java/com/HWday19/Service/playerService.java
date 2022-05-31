package com.HWday19.Service;

import com.HWday19.domain.players;

import java.util.List;

public interface playerService {
    players getPlayerById(long ID);
    List<players> getAll();
}
