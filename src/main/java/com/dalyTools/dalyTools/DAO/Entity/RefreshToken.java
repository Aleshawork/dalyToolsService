package com.dalyTools.dalyTools.DAO.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "refresh_token")
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name="refreshToken")
    protected String refreshToken;

    public RefreshToken(long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }
}
