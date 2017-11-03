package org.szymie.exercise.boundaries.adapters;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
