/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic.generalclasses;

import java.util.Date;

/**
 *
 * @author ijazm
 */
public class Record {
    public static Record r;
    private Integer id;
    private Date appointmentDate;

    public Record(Integer id, Date appointmentDate, Integer appointmentNo, Integer room, Integer patientId) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentNo = appointmentNo;
        this.room = room;
        this.patientId = patientId;

    }
    private Integer appointmentNo;
    private Integer room;
    private Integer patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(Integer appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    
    
}
