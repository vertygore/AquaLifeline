package com.AquaLifeLine.Backend.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AquaLifeLine.Backend.Kunde;
import com.AquaLifeLine.Backend.KundenService;
import com.AquaLifeLine.Backend.Login.JWT.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private KundenService kundenService;

    @Autowired private PasswordEncoder passwordEncoder;

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        Kunde kunde = new Kunde();
        if(kundenService.getKundeByName(request.getName()) != null) {
           return ResponseEntity.status(409).body("Name bereits vergeben");
        }
        kunde.setName(request.getName());
        // Passwort verschlüsseln
        kunde.setPassword(passwordEncoder.encode(request.getPassword()));
        kundenService.saveKunde(kunde);
        return ResponseEntity.ok("Registrierung erfolgreich");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Kunde kunde = kundenService.getKundeByName(request.getName());
        if (kunde == null || !passwordEncoder.matches(request.getPassword(), kunde.getPassword())){
            return ResponseEntity.status(401).body("Ungültige Anmeldedaten");
        }
        String token = jwtUtil.generateToken(kunde.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
    
