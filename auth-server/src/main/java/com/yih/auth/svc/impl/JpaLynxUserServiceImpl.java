package com.yih.auth.svc.impl;

import com.yih.auth.pojo.oauth2.AppScope;
import com.yih.auth.domain.user.AppUser;
import com.yih.auth.entity.AppGrantedAuthorityEntity;
import com.yih.auth.entity.UserEntity;
import com.yih.auth.repo.GrantedAuthorityRepo;
import com.yih.auth.repo.UserRepo;
import com.yih.auth.svc.LynxUserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaLynxUserServiceImpl implements LynxUserService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo repo;

    @Autowired
    private GrantedAuthorityRepo gaRepo;

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        Optional<UserEntity> userOp = repo.findByUsername(username);
        if (userOp.isPresent()) {
            List<AppGrantedAuthorityEntity> authority = gaRepo.findByUserId(userOp.get().getId());
            AppUser user = new AppUser(userOp.get(), authority.stream().map(AppGrantedAuthorityEntity::getAuthority).collect(Collectors.toList()));

            return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * create a new user
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public long create(String username, String password) {
        // save user
        UserEntity userEntity = new UserEntity(username, encoder.encode(password));

        repo.save(userEntity);
        // save granted authority
        AppGrantedAuthorityEntity entity = new AppGrantedAuthorityEntity(userEntity.getId(), AppScope.User.name());
        gaRepo.save(entity);
        return userEntity.getId();
    }

    @Transactional
    @Override
    public void remove(Long id) {
        repo.deleteById(id);
        // remove other entity;
        gaRepo.deleteByUserId(id);
    }

    @Override
    public void updatePassword(String password) {

    }

    @Override
    public AppUser findById(Long userId) {
        val user = repo.findById(userId);
        return user.map(userEntity -> new AppUser(userEntity, null)).orElse(null);
    }
}