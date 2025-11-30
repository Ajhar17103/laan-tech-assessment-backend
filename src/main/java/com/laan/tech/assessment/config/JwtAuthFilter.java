//package com.laan.tech.assessment.config;
//
//import com.laan.tech.assessment.auth.JwtService;
//import com.laan.tech.assessment.modules.user.entity.User;
//import com.laan.tech.assessment.modules.user.repo.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.*;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
//            throws ServletException, IOException {
//        String hdr = req.getHeader("Authorization");
//        if (StringUtils.hasText(hdr) && hdr.startsWith("Bearer ")) {
//            String token = hdr.substring(7);
//            try {
//                var jws = jwtService.parse(token);
//                String username = jws.getBody().getSubject();
//                Optional<User> userOpt = userRepository.findByUsername(username);
//                if (userOpt.isPresent() && Boolean.TRUE.equals(userOpt.get().getEnabled())) {
//                    var authClaim = (List<?>) jws.getBody().get("auth");
//                    var authorities = new ArrayList<SimpleGrantedAuthority>();
//                    if (authClaim != null) {
//                        for (Object a : authClaim) authorities.add(new SimpleGrantedAuthority(a.toString()));
//                    }
//                    var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (Exception ignored) {}
//        }
//        chain.doFilter(req, res);
//    }
//}
