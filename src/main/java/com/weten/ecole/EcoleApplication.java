package com.weten.ecole;

import com.weten.ecole.model.Role;
import com.weten.ecole.model.User;
import com.weten.ecole.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class EcoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoleApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        /*
        @Override
        public void run(String args) throws Exception {
                                                                };
         */
        //return (args) -> {}; pareil, un seul paramètre sans parenthèses
        return args -> {
            User user1 = new User();
            user1.setPassword("abcdef");
            user1.setUserName("user1");
            userService.addNewUser(user1);

            User user2 = new User();
            user2.setPassword("abcdef");
            user2.setUserName("admin");
            userService.addNewUser(user2);

            Stream.of("STUDENT","USER","ADMIN").forEach(roleName-> {
                Role role = new Role();
                role.setRoleName(roleName);
                role.setDescription(roleName+" joue son rôle correctement.");
                userService.addNewRole(role);
            });

            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","ADMIN");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","STUDENT");

            try {
                User user = userService.authenticate("admin","abcdef");
                System.out.println("UserId : "+user.getId());
                System.out.println("UserName :"+user.getUserName());
                user.getRoles().forEach(role -> {
                    System.out.println("User's roles : "+role.getRoleName()+","+role.getDescription());
                });

            }catch (Exception e){
                e.printStackTrace();
            }

        };
    }

}
