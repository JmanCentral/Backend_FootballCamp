package com.microservice.reservations.persistencies;

import com.microservice.reservations.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.fechaReserva = :fecha AND " +
            "((:inicio BETWEEN r.horaInicio AND r.horaFin) OR " +
            "(:fin BETWEEN r.horaInicio AND r.horaFin) OR " +
            "(r.horaInicio BETWEEN :inicio AND :fin))")
    List<Reservation> findConflictingReservations(@Param("fecha") LocalDate fecha,
                                                  @Param("inicio") LocalTime inicio,
                                                  @Param("fin") LocalTime fin);

    List<Reservation> findByUserId(Long userId);

    boolean existsByNombreReserva(String nombreReserva);

    List<Reservation> findAll();


}
