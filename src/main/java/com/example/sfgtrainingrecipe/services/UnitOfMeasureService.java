package com.example.sfgtrainingrecipe.services;

import com.example.sfgtrainingrecipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
