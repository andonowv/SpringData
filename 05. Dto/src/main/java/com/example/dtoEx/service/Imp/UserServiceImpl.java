package com.example.dtoEx.service.Imp;

import com.example.dtoEx.models.dto.UserLoginDto;
import com.example.dtoEx.models.dto.UserRegisterDto;
import com.example.dtoEx.models.entity.User;
import com.example.dtoEx.repository.UserRepository;
import com.example.dtoEx.service.UserService;
import com.example.dtoEx.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
        };
        Set<ConstraintViolation<UserRegisterDto>> violations =
                validationUtil.getViolations(userRegisterDto);

        if (violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violation = validationUtil.getViolations(userLoginDto);

        if (!violation.isEmpty()){
            violation.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository
                .findByEmailAndFullName(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if (user == null){
            System.out.println("Incorrect username / password");
        return;
        }
        loggedInUser = user;
    }

    @Override
    public void logout() {
        if (loggedInUser == null){
            System.out.println("cannot logout. No User was logged in.");
        }else {
            loggedInUser = null;
        }
    }
}
