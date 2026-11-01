package com.example.securitypo4.service;

import com.example.securitypo4.dto.UserRequest;
import com.example.securitypo4.entity.AppUser;
import com.example.securitypo4.entity.Role;
import com.example.securitypo4.repository.RoleRepository;
import com.example.securitypo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void saveUser(List<UserRequest> userRequests){
        for(UserRequest req : userRequests){
            AppUser user = new AppUser();
            user.setUsername(req.getUsername());
            user.setPassword(encoder.encode(req.getPassword()));
            user.setEnable(true);

            Set<Role> roleSet = new HashSet<>();
            for(String roleName: req.getRoles()){
                Role role = roleRepository.findByName(roleName)
                        .orElseGet(()-> {
                            Role newRole = new Role();
                            newRole.setName(roleName);
                            return roleRepository.save(newRole);
                        });
                roleSet.add(role);
            }
            user.setRoles(roleSet);
            userRepository.save(user);
        }
    }
}
