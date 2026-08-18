package br.com.limmazk.todolist.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
       var user = userRepository.findByUsername(userModel.getUsername());

       if (user != null){
            System.out.println("Usuario ja existe!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuario ja existe");
       }

       this.userRepository.save(userModel);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body("Usuario criado com sucesso");
    }
}
