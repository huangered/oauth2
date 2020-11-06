package com.yih.auth.svc.impl;

import com.yih.auth.domain.AppGrantedAuthority;
import com.yih.auth.domain.AppUser;
import com.yih.auth.entity.AppGrantedAuthorityEntity;
import com.yih.auth.entity.UserEntity;
import com.yih.auth.repo.GrantedAuthorityRepo;
import com.yih.auth.repo.UserRepo;
import com.yih.auth.svc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
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

    @Transactional
    @Override
    public boolean create(AppUser appUser) {
        // save user
        UserEntity userEntity = new UserEntity();
        repo.save(userEntity);
        // save granted authority
        List<AppGrantedAuthorityEntity> gas = appUser.getAuthorities()
                .stream()
                .map(aga -> new AppGrantedAuthorityEntity(userEntity.getId(), aga.getAuthority()))
                .collect(Collectors.toList());
        gaRepo.saveAll(gas);
        return true;
    }
}