package org.szymie.exercise.external.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.szymie.exercise.boundaries.adapters.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoderImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
