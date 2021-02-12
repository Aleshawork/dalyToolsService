package com.dalyTools.dalyTools.DAO.Repository;

import com.dalyTools.dalyTools.DAO.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
}
