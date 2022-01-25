package com.example.springjpaweb;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private WorkerRepository workerRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Worker worker = workerRepository.findWorkerByEmail(email);

        if (worker==null){
            throw new UsernameNotFoundException(email);
        }

        UserDetails user = User
                .withUsername(worker.getEmail())
                .password(worker.getPassword())
                .roles("WORKER", "BOSS", "ADMIN")
                .build();

        return user;
    }
}
