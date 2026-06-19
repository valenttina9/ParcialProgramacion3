package programacion3.Parcial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EcoRideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaDesbloquearVehiculoConPagoExitoso() throws Exception {
        String requestBody = """
                {
                  "idUsuario": 2,
                  "patente": "BICI456",
                  "metodoPago": "TARJETA",
                  "minutosViaje": 3
                }
                """;

        mockMvc.perform(post("/api/alquileres/desbloquear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value("BICI456"))
                .andExpect(jsonPath("$.tipoVehiculo").value("Bicicleta electrica"))
                .andExpect(jsonPath("$.estadoActual").value("EN_VIAJE"))
                .andExpect(jsonPath("$.criterioTarifaAplicado").value("ESTANDAR"))
                .andExpect(jsonPath("$.montoCobrado").value(12150.0));
    }

    @Test
    void deberiaCambiarCriterioHoraPicoYAplicarlo() throws Exception {
        String requestBody = """
                {
                  "idUsuario": 1,
                  "patente": "MONO123",
                  "metodoPago": "BILLETERA",
                  "minutosViaje": 2
                }
                """;

        mockMvc.perform(post("/api/tarifas/criterio")
                        .param("criterio", "HORA_PICO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.criterioActivo").value("HORA_PICO"));

        mockMvc.perform(post("/api/alquileres/desbloquear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.criterioTarifaAplicado").value("HORA_PICO"))
                .andExpect(jsonPath("$.montoCobrado").value(14000.0));
    }

    @Test
    void deberiaBloquearVehiculoEnReparacion() throws Exception {
        String requestBody = """
                {
                  "idUsuario": 1,
                  "patente": "MONO123",
                  "metodoPago": "TARJETA",
                  "minutosViaje": 1
                }
                """;

        mockMvc.perform(post("/api/vehiculos/MONO123/reparacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoActual").value("EN_REPARACION"));

        mockMvc.perform(post("/api/alquileres/desbloquear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("No se puede iniciar un viaje con un vehiculo en reparacion."));
    }

    @Test
    void deberiaDepurarReportesGpsDuplicadosEnUnaSolaPasada() throws Exception {
        String requestBody = """
                {
                  "reportes": [
                    {"patente":"MONO123","latitud":-34.60,"longitud":-58.38,"timestamp":1000},
                    {"patente":"MONO123","latitud":-34.60,"longitud":-58.38,"timestamp":1000},
                    {"patente":"BICI456","latitud":-34.61,"longitud":-58.40,"timestamp":1001}
                  ]
                }
                """;

        mockMvc.perform(post("/api/gps/depurar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidadOriginal").value(3))
                .andExpect(jsonPath("$.cantidadUnica").value(2))
                .andExpect(jsonPath("$.reportesUnicos.length()").value(2));
    }
}
