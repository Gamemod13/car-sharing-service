# Developing rules:

## Important links

* [Naming Convention](https://www.scaler.com/topics/git/git-branch-naming-conventions/)
* [Trello board](https://trello.com/b/hxDM3O1P/service-development)
* [Project requirements](https://docs.google.com/document/d/1UIQYBip_OUnSRkVebzRLs8ZvH4ODSLpGMnUBnv4CfRA/edit)

## Branching strategy

For branches that are created for bug fixes, the branch must start with a word **bugfix**.
For branches that are created for implementation some feature, the branch must start with 
a word **feature**.
For tests implementation - we are use prefix **test**"

Example

* ```feature-create-rental-entity```
* ```bugfix-user-not-initialize```
* ```test-user-service```

### Commits

* Please, sure that you remove your ticket in Trello from "In Progress" to "On Review"
* Please provide a descriptive messages
Example: ```Added implementation of UserController with all required methods```
* Use rebase

### DAO/DTO/Service/Controller examples:

* Please, sure that you make your implementation consistently by other code:
* Examples DAO:
```agsl

public interface UserRepository 
extends JpaRepository<User, Long>{
  Optional<User> findByEmail(String email);
 } 
```
* Examples DTO:
```agsl

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
```
* Examples Controller:
```agsl

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping("/me")
    public UserResponseDto get(Authentication auth) {
       ...
    }

    @PutMapping("{id}/role")
    public UserResponseDto updateRole(@PathVariable Long id, @RequestBody String role) {
        ...
    }

    @PutMapping("/me")
    public UserResponseDto updateUser(Authentication auth, @RequestBody UserRequestDto dto) {
        ...
    }
}

```