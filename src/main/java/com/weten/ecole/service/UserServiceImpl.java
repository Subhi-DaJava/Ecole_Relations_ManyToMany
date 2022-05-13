package com.weten.ecole.service;

import com.weten.ecole.model.Role;
import com.weten.ecole.model.User;
import com.weten.ecole.repository.RoleRepository;
import com.weten.ecole.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    //l'injection de dépendances via le constructeur avec paramètres
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User addNewUser(User newUser) {
        //si userId est String
        //user.setUserId(UUID.randomUUID().toString()); une chaîne des caractères unique

        //ici on peut hash code le mot de passe
        return userRepository.save(newUser);
    }

    @Override
    public Role addNewRole(Role newRole) {

        return roleRepository.save(newRole);
    }

    @Override
    public User findUserByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User userByName = findUserByUserName(userName);
        Role roleByName = findRoleByRoleName(roleName);

        //Méthode transactionnelle va mettre à jour automatiquement dans la base de données, comme modifications etc
        //if (userByName.getRoles() != null){
            userByName.getRoles().add(roleByName);
            roleByName.getUsers().add(userByName);
        //}
        //pas besoin de faire "userRepository.save(userByName)
    }

    @Override
    public User authenticate(String userName, String password) {
        //en principe c'est Spring Security s'en occupe
       User user = userRepository.findByUserName(userName);
       if(user==null){
           throw new RuntimeException("Bad credentials : vérifiez le nom ou le mot de passe");
       }
       if(user.getPassword().equals(password)){
           return user;
       }
       throw new RuntimeException("Bad credentials : vérifiez le nom ou le mot de passe");
    }
}
