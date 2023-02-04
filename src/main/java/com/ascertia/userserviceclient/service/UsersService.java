package com.ascertia.userserviceclient.service;


import com.ascertia.userserviceclient.Adapter.AdminAdapter;
import com.ascertia.userserviceclient.dto.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsersService {

    @Autowired
    AdminAdapter adminAdapter;

    public void saveUser(UsersDTO usersDTO) {
        log.info("In UserService.saveUser()");
        adminAdapter.callAdminForCreateUser(usersDTO);
    }

    public void updateUser(UsersDTO usersDTO) {
        log.info("In UserService.updateUser()");
        adminAdapter.callAdminForUpdateUser(usersDTO);
    }

    public List<UsersDTO> getAllUsers() {
        log.info("In UserService.getAllUsers()");
        return adminAdapter.callAdminForGetAllUsers();
    }

    public List<UsersDTO> searchUser(UsersDTO usersDTO) {
        log.info("In UserService.searchUser()");
        return adminAdapter.callAdminForSearchUser(usersDTO);
    }

    public void deleteUser(Long id) {
         adminAdapter.callAdminForDeleteUser(id);
    }
}


