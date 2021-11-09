package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class UserRepository implements CrudRepository<AppUser, String> {

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser findById(String id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {
        try (FileWriter dataWriter = new FileWriter("database/users.txt", true)) {
            newUser.setId(UUID.randomUUID().toString());
            String userRecord = newUser.toFileString();
            dataWriter.write(userRecord + "\n");
            return newUser;
        } catch (IOException e) {
            throw new ResourcePersistenceException(e);
        }
    }

    @Override
    public boolean update(AppUser updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

}
