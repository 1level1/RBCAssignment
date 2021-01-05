package com.example.services;

import com.example.Client;
import com.example.msgfacades.ClientMsg;

public interface IClientService {
    public Client sndClientMsg(ClientMsg clntMsg);
}
