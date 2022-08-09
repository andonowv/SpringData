package com.example.dtoEx;

import com.example.dtoEx.models.dto.GameAddDto;
import com.example.dtoEx.models.dto.UserLoginDto;
import com.example.dtoEx.models.dto.UserRegisterDto;
import com.example.dtoEx.service.GameService;
import com.example.dtoEx.service.UserService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;

    private final GameService gameService;

    public CommandLineRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("Enter commands");
            String [] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]){
                case "RegisterUser" -> userService.registerUser(new UserRegisterDto(
                        commands [1],
                        commands [2],
                        commands [3],
                        commands [4]
                ));
                case "LoginUser" -> userService
                        .loginUser(new UserLoginDto(commands [1], commands [2]));

                case "Logout" -> userService
                        .logout();
                case "AddGame" -> gameService
                        .addGame(new GameAddDto(commands [1], new BigDecimal(commands[2]), Double.parseDouble(commands[3]),
                                commands [4], commands [5], commands [6], commands [7]));
            }
        }
    }
}
