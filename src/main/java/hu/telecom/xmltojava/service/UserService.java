package hu.telecom.xmltojava.service;

import java.util.List;

public interface UserService {

   String createUser(generated.User user);
   List<generated.User> getAllUsers();
}
