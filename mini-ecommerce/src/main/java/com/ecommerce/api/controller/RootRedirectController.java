package com.ecommerce.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RootRedirectController {

    // Redirect common top-level resource paths to the correct API base path
    @GetMapping({"/products", "/customers", "/orders", "/suppliers"})
    public ResponseEntity<Void> redirectCommonResources(org.springframework.web.context.request.NativeWebRequest webRequest) {
        String path = webRequest.getNativeRequest(jakarta.servlet.http.HttpServletRequest.class).getRequestURI();
        // path like "/products" -> suffix is the same segment
        String location = "/api/v1/ecommerce" + path;
        return ResponseEntity.status(302).header("Location", location).build();
    }

    // Optional: Redirect singular variants as well (e.g., /product -> /api/v1/ecommerce/products)
    @GetMapping({"/product", "/customer", "/order", "/supplier"})
    public ResponseEntity<Void> redirectSingular(org.springframework.web.context.request.NativeWebRequest webRequest) {
        String path = webRequest.getNativeRequest(jakarta.servlet.http.HttpServletRequest.class).getRequestURI();
        String plural;
        switch (path) {
            case "/product": plural = "/products"; break;
            case "/customer": plural = "/customers"; break;
            case "/order": plural = "/orders"; break;
            case "/supplier": plural = "/suppliers"; break;
            default: plural = path; // fallback
        }
        String location = "/api/v1/ecommerce" + plural;
        return ResponseEntity.status(302).header("Location", location).build();
    }
}
