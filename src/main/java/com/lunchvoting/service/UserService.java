package com.lunchvoting.service;

import com.lunchvoting.AuthorizedUser;
import com.lunchvoting.model.User;
import com.lunchvoting.repository.UserRepository;
import com.lunchvoting.to.UserTo;
import com.lunchvoting.util.UserUtil;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.List;

import static com.lunchvoting.util.UserUtil.prepareToSave;
import static com.lunchvoting.util.ValidationUtil.checkNotFound;
import static com.lunchvoting.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


//    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

//    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return repository.getById(id)
                .orElseThrow(()-> new NotFoundException("id=" + id));
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return repository.getByEmail(email)
                .orElseThrow(() -> new NotFoundException("email=" + email));
    }

//    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

//    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
//      checkNotFoundWithId : check works only for JDBC, disabled
        prepareAndSave(user);
    }

//    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

//    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " is not found"));
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(user);
//        return repository.save(prepareToSave(user, passwordEncoder));
    }

}