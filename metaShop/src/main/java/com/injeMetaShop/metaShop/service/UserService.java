package com.injeMetaShop.metaShop.service;

import com.injeMetaShop.metaShop.authorize.jwt.AuthDto;
import com.injeMetaShop.metaShop.entity.User;
import com.injeMetaShop.metaShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void registerUser(AuthDto.SignupDto signupDto) {
        validateDuplicateMember(signupDto.getEmail());
        User user = User.registerUser(signupDto);
        userRepository.save(user);
    }
    @Transactional
    public void registerAdmin(AuthDto.SignupDto signupDto) {
        validateDuplicateMember(signupDto.getEmail());
        User user = User.registerAdmin(signupDto);
        userRepository.save(user);
    }

    private void validateDuplicateMember(String email){
        Optional<User> userModel = userRepository.findByEmail(email);
        if (userModel.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<String> checkUserPurchase(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getPurchase();
    }
    public List<User> allMember()  {
        return userRepository.findAll();
    }

}
