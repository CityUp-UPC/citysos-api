package com.citysos.api.incident.domain.persistence;

import com.citysos.api.incident.domain.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    List<Incident> findByDistrict(String district);

    @Query("SELECT i FROM Incident i JOIN i.police p WHERE p.id = :policeId")
    List<Incident> fetchByPoliceId(Integer policeId);

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE status = 'PENDIENT'")
    List<Incident> findIncidentPendient();

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE status = 'IN_PROGRESS'")
    List<Incident> findIncidentInProgress();

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE status = 'HELP_REQUIRED'")
    List<Incident> findIncidentHelp();

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE citizen_id = :citizenId")
    List<Incident> findByCitizenId(Integer citizenId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO incident_police (incident_id, police_id) VALUES (:incidentId, :policeId)")
    void insertIncidentPolice(Integer policeId, Integer incidentId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE incidents SET status = 'IN_PROGRESS' WHERE id = :incidentId")
    void updateIncidentStatus(Integer incidentId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE incidents SET status = 'COMPLETED' WHERE id = :incidentId")
    void completedIncident(Integer incidentId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE incidents SET status = 'HELP_REQUIRED' WHERE id = :incidentId")
    void requestReinforcements(Integer incidentId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM incident_police WHERE incident_id = :incidentId AND police_id = :policeId")
    int existsPoliceInIncident(Integer policeId, Integer incidentId);

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE status != 'COMPLETED' AND citizen_id = :citizenId")
    List<Incident> findByStatusPendingByCitizenId(Integer citizenId);

    @Query(nativeQuery = true, value = "SELECT * FROM incidents WHERE " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude)))) < :km")
    List<Incident> findIncidentsNearLocation(Double latitude, Double longitude, Double km);
}
