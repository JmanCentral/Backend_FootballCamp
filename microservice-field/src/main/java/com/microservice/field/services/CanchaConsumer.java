package com.microservice.field.services;

import com.microservice.field.entities.Estado;
import com.microservice.field.http.response.ReservaEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CanchaConsumer {

    @Autowired
    private IFieldService fieldService;

    @KafkaListener(topics = "reservas-topic", groupId = "grupo-canchas")
    public void escucharEventoReserva(ReservaEventDTO evento) {
        System.out.println("Evento recibido: " + evento);
        // Actualizamos el estado de la cancha usando el servicio
        fieldService.actualizarEstadoCancha(
                evento.getFieldId(),
                Estado.valueOf(evento.getStatus())
        );
    }
}
