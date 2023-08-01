package com.example.springresourceserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt){

        Collection<GrantedAuthority> authorities=new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("MANAGER"));
        return authorities;
    }

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter  = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(defaultGrantedAuthoritiesConverter.convert(source)
                                .stream(),
                        extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }
}
