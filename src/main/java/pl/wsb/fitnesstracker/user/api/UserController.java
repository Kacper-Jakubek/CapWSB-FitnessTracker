package pl.wsb.fitnesstracker.user.api;

import lombok.RequiredArgsConstructor;
import pl.wsb.fitnesstracker.user.internal.UserMapper;
import pl.wsb.fitnesstracker.user.internal.UserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for user operations.
 * Provides basic CRUD endpoints for users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return a list of all users as {{@link UserDto}}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves basic info of all users.
     *
     * @return a list of all users as {{@link SimpleUserDto}}
     */
    @GetMapping("/simple")
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Retrieves user with given id.
     *
     * @return a given user as {{@link UserDto}}
     */
    @GetMapping("/{id}")
    public Optional<UserDto> getUserDetailsById(@PathVariable Long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .findFirst();
    }

    /**
     * Deletes user with given id from database.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Retrieves users that contain given string in their emails.
     *
     * @return a list of users with matching emails as {{@link UserDto}}
     */
    @GetMapping("/email")
    public List<UserDto> getUserDetailsByEmail(@RequestParam(required = false) String email) {
        return userService.getUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    /**
     * Retrieves users that were born before given date.
     *
     * @return a list of all older than given date users as {{@link UserDto}}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Adds a new user based on the provided UserDto data.
     *
     * @param userDto the user data transfer object with information to create a new user
     * @return {{@link User}} entity saved to database
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * Updates user's data based on the provided UserDto data and given id.
     *
     * @param userDto the user data transfer object with new informations about user
     * @return {{@link User}} entity updated in database
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody UserDto userDto, @PathVariable Long id) throws InterruptedException {
        return userService.updateUser(userMapper.toEntity(userDto), id);
    }

}