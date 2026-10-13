package com.api.libreria.repositories;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.libreria.models.PrestamoModel;

@Repository
public interface IPrestamoRepository extends JpaRepository<PrestamoModel, Long> {
    ArrayList<PrestamoModel> findByUsuarioId(Long usuarioId);


}
