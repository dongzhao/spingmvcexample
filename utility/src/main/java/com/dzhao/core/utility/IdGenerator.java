package com.dzhao.core.utility;

import java.util.UUID;

/**
 * Created by dzhao on 19/08/2015.
 */
public class IdGenerator {

    private IdGenerator(){}

    public static String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
